package com.iwind.tj_tax;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.easemob.easeui.ui.EaseBaseActivity;
/**
 * 作者：HuGuoJun
 * 2016/6/2 10:35
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_Splash extends EaseBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__splash);
        InitView();
    }

    @Override
    public void InitView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(context, MainActivity.class));
                finish();
            }
        }, 3000);
    }

    @Override
    public void InitData() {

    }

    @Override
    public void setOnClickListener() {

    }

    @Override
    public void onClick(View view) {

    }
}
