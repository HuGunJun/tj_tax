package com.iwind.tj_tax.Mine;

import android.os.Bundle;
import android.view.View;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.widget.EaseTitleBar;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 我的提问页面
 * 作者：HuGuoJun
 * 2016/6/12 17:40
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_MyQuestion extends EaseBaseActivity {

    @ViewInject(R.id.title_bar)
    EaseTitleBar title_bar;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.act_my_question);
        ViewUtils.inject(this);
        InitView();
        InitData();
        setOnClickListener();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void InitView() {
        title_bar.setLeftImageResource(R.drawable.ease_mm_title_back);
        title_bar.setTitle(getResources().getString(R.string.my_question));
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
