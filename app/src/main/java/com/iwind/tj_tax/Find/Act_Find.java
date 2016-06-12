package com.iwind.tj_tax.Find;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;

/**
 * 作者：HuGuoJun
 * 2016/6/3 14:25
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_Find extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__find);
        ViewUtils.inject(this);
        InitView();
        InitData();
    }

    public void InitView() {

    }

    public void InitData() {

    }

    public void setOnClickListener() {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lv_should_know:
                startActivity(new Intent(getApplicationContext(), Act_Know.class));
                break;
            case R.id.rl_news:
                startActivity(new Intent(getApplicationContext(), Act_News.class));
                break;
        }
    }
}
