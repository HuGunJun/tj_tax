package com.iwind.tj_tax.HomePage;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.widget.EaseTitleBar;
import com.easemob.easeui.widget.xlistview.XListView;
import com.iwind.Constant.ConstantString;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 行业或者税种选择
 * 作者：HuGuoJun
 * 2016/6/12 09:11
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_Select_Choice extends EaseBaseActivity {
    @ViewInject(R.id.title_bar)
    EaseTitleBar mTitleBar;
    @ViewInject(R.id.lv_choice)
    XListView lv_choice;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_act__select_choice);
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
        mTitleBar.setTitle(getIntent().getExtras().getString(ConstantString.TYPE).equals("1") ? getResources().getString(R.string.industry_choice) :
                getResources().getString(R.string.tax_type_choice));
        mTitleBar.setLeftImageResource(R.drawable.ease_mm_title_back);
        mTitleBar.setRightText(getResources().getString(R.string.dl_ok));
        lv_choice.setPullLoadEnable(false);
        lv_choice.setPullRefreshEnable(false);
    }

    @Override
    public void InitData() {

    }

    @Override
    public void setOnClickListener() {
        mTitleBar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"确定",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
