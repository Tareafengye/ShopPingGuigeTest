package com.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.activity.R;
import com.util.DensityUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/***
 * BaseActivity封装，不用findViewbyid得到控件ID，沉侵式状态栏自定义，侧滑返回
 * setStatusBarColor(R.color.colorAccent);//默认颜色
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    private View statusBar;
    private LinearLayout container;
    /**
     * 是否开启  右划关闭activity 手势，默认开启
     */
    private boolean isGestureOpen = true;

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            statusBar = new View(this);
            ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight());
            //添加状态栏VIew
            statusBar.setLayoutParams(param);
            setStatusBarColor(R.color.colorAccent);//默认颜色

            container = new ViewContainer(getApplicationContext());
            container.setOrientation(LinearLayout.VERTICAL);
            container.addView(statusBar);
        }

    }

    public abstract void onClick(View view);

    /**
     * 设置状态栏颜色，
     *
     * @param color 颜色资源id， 如 R.color.orange</br>
     *              0 黑色
     */
    protected void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (color == 0) {
                statusBar.setBackgroundColor(Color.BLACK);//默认黑色
            } else {
                statusBar.setBackgroundColor(getResources().getColor(color));
            }


        }
    }

    /***
     * 统一设置ID
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            LayoutInflater.from(this).inflate(layoutResID, container, true);
            setContentView(container);

        } else {
            super.setContentView(layoutResID);
        }
        smartInject();
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    protected int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * 设置是否允许屏幕左侧 快速 右划关闭activity，默认允许
     *
     * @param b
     */
    protected void isGestureSensitive(boolean b) {
        isGestureOpen = b;
    }

    private void smartInject() {

        try {
            Class<? extends Activity> clz = getClass();

            while (clz != BaseActivity.class) {

                Field[] fs = clz.getDeclaredFields();
                Resources res = getResources();
                String packageName = getPackageName();
                for (Field field : fs) {
                    if (!View.class.isAssignableFrom(field.getType())) {
                        continue;
                    }
                    int viewId = res.getIdentifier(field.getName(), "id", packageName);
                    if (viewId == 0)
                        continue;
                    field.setAccessible(true);
                    try {
                        View v = findViewById(viewId);
                        field.set(this, v);
                        Class<?> c = field.getType();
                        Method m = c.getMethod("setOnClickListener", android.view.View.OnClickListener.class);
                        m.invoke(v, this);
                    } catch (Throwable e) {
                    }
                    field.setAccessible(false);


                }

                clz = (Class<? extends Activity>) clz.getSuperclass();

            }
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    /**
     * 屏幕左侧右划返回容器 ,
     *
     * @author Young
     */
    private class ViewContainer extends LinearLayout {


        private int leftMargin;
        private VelocityTracker tracker;
        private float startX;
        private float startY;

        public ViewContainer(Context context) {
            super(context);
            leftMargin = DensityUtil.dip2px(35);

        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            if (isGestureOpen == false) {
                return super.dispatchTouchEvent(ev);
            }
            switch (ev.getAction()) {
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
//当满足下面条件时 视为 右划关闭手势
//起始按压位置x坐标小与leftMargin&& 向右滑动                       &&           向右滑动距离    >   竖直方向距离
                    if (startX < leftMargin && ev.getRawX() > startX && ev.getRawX() - startX > Math.abs(ev.getRawY() - startY)) {
//速度大于2500时关闭activity
                        tracker.computeCurrentVelocity(1000);
                        if (tracker.getXVelocity() > 2500) {
                            finish();
                        }

                    }

                    tracker.recycle();
                    break;


                case MotionEvent.ACTION_DOWN:
                    startX = ev.getRawX();
                    startY = ev.getRawY();
                    tracker = VelocityTracker.obtain();
                    tracker.addMovement(ev);
                    break;
                case MotionEvent.ACTION_MOVE:
                    tracker.addMovement(ev);
                    break;
            }


            return super.dispatchTouchEvent(ev);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (isGestureOpen == false) {
                return super.onTouchEvent(event);
            }
            return true;
        }

    }


}
