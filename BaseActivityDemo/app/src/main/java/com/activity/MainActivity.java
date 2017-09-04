package com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.base.BaseActivity;
import com.bean.GoodsDetailBean;
import com.dialog.JiaRuGouWuCheDialog;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends BaseActivity implements Observer {
    private Button btnStart;
    private String jsonString;
    GoodsDetailBean data;
    JiaRuGouWuCheDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBarColor(R.color.colorPrimaryDark);//状态栏颜色
        initJsonData();
        getData();
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
        data = gson.fromJson(jsonString, GoodsDetailBean.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                if (dialog == null)
                    dialog = new JiaRuGouWuCheDialog(data, false);
                dialog.show(getSupportFragmentManager(), "");
                break;
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        Toast.makeText(MainActivity.this, "" + data.toString(), Toast.LENGTH_LONG).show();
    }
}
