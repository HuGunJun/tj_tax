package com.iwind.tj_tax.HomePage;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.widget.EaseTitleBar;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 添加回答
 * 作者：HuGuoJun
 * 2016/6/8 13:09
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_Add_Answer extends EaseBaseActivity {

    @ViewInject(R.id.title_bar)
    EaseTitleBar mEaseTitleBar;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_act__add_answer);
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
        mEaseTitleBar.setLeftImageResource(R.drawable.ease_mm_title_back);
        mEaseTitleBar.setTitle(getResources().getString(R.string.add_answer));
        mEaseTitleBar.setRightText(getResources().getString(R.string.brown));
    }


    @Override
    public void InitData() {

    }

    @Override
    public void setOnClickListener() {
        mEaseTitleBar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mEaseTitleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"发布",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
