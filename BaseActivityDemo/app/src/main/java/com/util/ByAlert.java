package com.util;

import android.widget.Toast;

import com.app.App;

/**
 * Created by Administrator on 2017/9/4 0004.
 */

public class ByAlert {
    public static void alert(Object info) {
        if (info != null) {
            if (info.equals("用户ID缺失") || info.equals("UID不能为空")) {
                Toast.makeText(App.getInstance(), "亲,你还未登录,登录后更精彩",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(App.getInstance(), info+"", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
