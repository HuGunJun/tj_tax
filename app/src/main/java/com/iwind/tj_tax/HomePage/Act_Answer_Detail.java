package com.iwind.tj_tax.HomePage;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.widget.EaseTitleBar;
import com.iwind.Constant.ConstantString;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 回答详情
 * 作者：HuGuoJun
 * 2016/6/8 11:56
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_Answer_Detail extends EaseBaseActivity {
    @ViewInject(R.id.title_bar)
    EaseTitleBar mTitleBar;
    @ViewInject(R.id.lv_question_describe)
    LinearLayout lv_question_describe;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_act__answer__detail);
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
        mTitleBar.setLeftImageResource(R.drawable.ease_mm_title_back);
        mTitleBar.setRightImageResource(R.drawable.ic_launcher);
        mTitleBar.setTitle(getIntent().getExtras().getString(ConstantString.DISSCUSS_TITLE));
        mTitleBar.setLeftImageResource(R.drawable.ease_mm_title_back);
    }

    @Override
    public void InitData() {

    }

    @Override
    public void setOnClickListener() {
        mTitleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "分享", Toast.LENGTH_SHORT).show();
            }
        });
        mTitleBar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setCenterLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lv_question_describe.getVisibility() == View.VISIBLE) {
                    lv_question_describe.setVisibility(View.GONE);
                } else {
                    lv_question_describe.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
