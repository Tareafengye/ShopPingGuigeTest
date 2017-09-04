package com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import com.azhong.customview.R;
import com.google.gson.Gson;
import com.bean.GoodsBean;
import com.goodsinterface.OnSelectedListener;
import com.weight.ShoppingSelectView;

import java.io.InputStream;


public class MainActivity extends AppCompatActivity implements OnSelectedListener {
    private ShoppingSelectView view;
    String jsonString;
    GoodsBean data;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (ShoppingSelectView) findViewById(R.id.v);
        //设置监听需要在设置数据源之前
        view.setOnSelectedListener(this);
        initJsonData();
        getData();
        initData();
    }

    //本地数据测试专用
    private void initJsonData() {
        try {
            InputStream is = getAssets().open("specs.json");//打开json数据
            byte[] by = new byte[is.available()];//转字节
            is.read(by);
            jsonString = new String(by, "utf-8");
            is.close();//关闭流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //解析Json数据
    private void getData() {
        Gson gson = new Gson();
        data = gson.fromJson(jsonString, GoodsBean.class);
    }

    private void initData() {

        //最终数据
        view.setData(data);
        Log.d(TAG, data + "");
    }

    @Override
    public void onSelected(String title, String smallTitle, int id) {
        Toast.makeText(this, title + "--" + smallTitle + "--" + id, Toast.LENGTH_SHORT).show();
    }
}
