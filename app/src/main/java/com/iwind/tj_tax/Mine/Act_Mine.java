package com.iwind.tj_tax.Mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;

/**
 * 我的主页
 * 作者：HuGuoJun
 * 2016/6/3 13:31
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_Mine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__mine);
        ViewUtils.inject(this);
    }

    public void InitView() {

    }

    public void InitData() {

    }

    public void setOnClickListener() {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_myinfo:
                startActivity(new Intent(getApplicationContext(), Act_MyInfo.class));
                break;
            case R.id.rl_my_question:
                startActivity(new Intent(getApplicationContext(), Act_MyQuestion.class));
                break;
            case R.id.rl_my_answer:
                break;
            case R.id.rl_my_collection:
                startActivity(new Intent(getApplicationContext(),Act_MyCollection.class));
                break;
            case R.id.rl_user_callback:
                break;
            case R.id.rl_setting:
                break;
        }
    }
}
