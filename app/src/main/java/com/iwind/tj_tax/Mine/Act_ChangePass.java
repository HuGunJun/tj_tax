package com.iwind.tj_tax.Mine;

import android.os.Bundle;
import android.view.View;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.widget.EaseTitleBar;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 修改密码
 * 作者：HuGuoJun
 * 2016/6/13 14:59
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_ChangePass extends EaseBaseActivity {
    @ViewInject(R.id.title_bar)
    EaseTitleBar title_bar;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.act_change_pass);
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
        title_bar.setTitle(getResources().getString(R.string.change_pass));
        title_bar.setRightText(getResources().getString(R.string.commit));
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
        title_bar.setRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Commit();
            }
        });
    }

    /**
     * 修改密码
     */
    private void Commit() {

        /**
         * this area is for commit pass
         */
        finish();
    }
}
