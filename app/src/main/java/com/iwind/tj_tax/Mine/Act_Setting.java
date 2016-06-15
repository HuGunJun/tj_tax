package com.iwind.tj_tax.Mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.widget.EaseTitleBar;
import com.iwind.App.MyApplication;
import com.iwind.tj_tax.MainActivity;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 设置页面
 * 作者：HuGuoJun
 * 2016/6/13 09:38
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_Setting extends EaseBaseActivity {
    @ViewInject(R.id.title_bar)
    EaseTitleBar title_bar;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.act_setting);
        ViewUtils.inject(this);
        InitView();
        InitData();
        setOnClickListener();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_change_pass:
                startActivity(new Intent(context, Act_ChangePass.class));
                break;
            case R.id.btn_quite:
                MyApplication.clearUserInfo();
                MainActivity.instance.finish();
                finish();
                break;
        }
    }

    @Override
    public void InitView() {
        title_bar.setTitle(getResources().getString(R.string.setting));
        title_bar.setLeftImageResource(R.drawable.ease_mm_title_back);
    }

    @Override
    public void InitData() {

    }

    @Override
    public void setOnClickListener() {
        title_bar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
