package com.iwind.tj_tax.Question;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.utils.EaseAnimationUtils;
import com.easemob.easeui.widget.EaseTitleBar;
import com.iwind.Constant.ConstantString;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 作者：HuGuoJun
 * 2016/6/8 15:28
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_Question_Detail extends EaseBaseActivity {
    @ViewInject(R.id.title_bar)
    EaseTitleBar mEaseTitleBar;
    @ViewInject(R.id.lv_question_describe)
    LinearLayout lv_question_describe;
    @ViewInject(R.id.scrollView)
    ScrollView scrollView;
    @ViewInject(R.id.bottom_tool)
    LinearLayout bottom_tool;
    //记录手指是否向上滑动
    private boolean isUpSlide;
    private float viewSlop;
    //按下的y坐标
    private float lastY;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_act__question_detail);
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
        mEaseTitleBar.setTitle(getIntent().getExtras().getString(ConstantString.QUESTION_TITLE));
        mEaseTitleBar.setLeftImageResource(R.drawable.ease_mm_title_back);
        mEaseTitleBar.setRightImageResource(R.drawable.ic_launcher);
        viewSlop = ViewConfiguration.get(this).getScaledTouchSlop();
    }

    @Override
    public void InitData() {

    }

    @Override
    public void setOnClickListener() {
        mEaseTitleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "分享", Toast.LENGTH_SHORT).show();
            }
        });
        mEaseTitleBar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mEaseTitleBar.setCenterLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lv_question_describe.getVisibility() == View.VISIBLE) {
                    lv_question_describe.setVisibility(View.GONE);
                } else {
                    lv_question_describe.setVisibility(View.VISIBLE);
                }
            }
        });
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        lastY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float disY = event.getY() - lastY;
                        //垂直方向滑动
                        if (Math.abs(disY) > viewSlop) {
                            //设置了TextView的点击事件之后，会导致这里的disY的数值出现跳号现象，最终导致的效果就是
                            //下面的tool布局在手指往下滑动的时候，先显示一个，然后再隐藏，这是完全没必要的
                            //是否向上滑动
                            isUpSlide = disY < 0;
                            //实现底部tools的显示与隐藏
                            if (isUpSlide) {
                                if (!EaseAnimationUtils.isToolHide())
                                    EaseAnimationUtils.hideTool(getWindow().getDecorView().getHeight(), bottom_tool, Act_Question_Detail.this);
                            } else {
                                if (EaseAnimationUtils.isToolHide())
                                    EaseAnimationUtils.showTool(getWindow().getDecorView().getHeight(), bottom_tool, Act_Question_Detail.this);
                            }
                        }

                        lastY = event.getY();
                        break;
                }

                return false;
            }
        });

    }
}
