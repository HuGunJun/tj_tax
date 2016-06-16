package com.iwind.tj_tax.HomePage;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.utils.EaseAnimationUtils;
import com.easemob.easeui.widget.EaseTitleBar;
import com.easemob.util.DensityUtil;
import com.iwind.Constant.ConstantString;
import com.iwind.Widget.MyScrollView;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 回答详情
 * 作者：HuGuoJun
 * 2016/6/8 11:56
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_Answer_Detail extends EaseBaseActivity {

    @ViewInject(R.id.title_bar)
    private EaseTitleBar title_bar;
    @ViewInject(R.id.tv_question_describe)
    private TextView tv_question_describe;
    @ViewInject(R.id.bottom_tool)
    private RelativeLayout bottom_tool;
    @ViewInject(R.id.lv_answer)
    private LinearLayout lv_answer;
    @ViewInject(R.id.scroller)
    private MyScrollView mScroller;
    @ViewInject(R.id.ll_top)
    private FrameLayout fl_top;

    private GestureDetector mGestureDetector;
    private float viewSlop;
    //按下的y坐标
    private float lastY;
    //记录手指是否向上滑动
    private boolean isUpSlide;
    //工具栏是否是隐藏状态
    private boolean isToolHide;
    //上部布局是否是隐藏状态
    private boolean isTopHide = false;
    //是否已经完成测量
    private boolean isMeasured = false;
    //顶部布局隐藏的检测距离
    private static final int TOP_DISTANCE_Y = 120;
    //默认的动画时间
    private static final int TIME_ANIMATION = 300;
    //是否在顶部布局的滑动范围内
    private boolean isInTopDistance = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__answer__detail);
        com.lidroid.xutils.ViewUtils.inject(this);
        InitView();
        InitData();
        setOnClickListener();


    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void InitView() {

        title_bar.setTitle(getIntent().getExtras().getString(ConstantString.DISSCUSS_TITLE));
        title_bar.setLeftImageResource(R.drawable.ease_mm_title_back);

        viewSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        //获取Bar和Title的高度，完成auther布局的margenTop设置
        ViewTreeObserver viewTreeObserver = fl_top.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (!isMeasured) {
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout
                            .LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0, title_bar.getHeight() + tv_question_describe.getHeight(), 0, 0);
                    lv_answer.setLayoutParams(layoutParams);
                    isMeasured = true;
                }
                return true;
            }
        });
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
        mGestureDetector = new GestureDetector(this, new DetailGestureListener());
        mScroller.setBottomListener(new MyScrollView.BottomListener() {
            @Override
            public void onBottom() {
                if (isToolHide) {
                    showTool();
                }
            }
        });
        mScroller.setScrollListener(new MyScrollView.onScrollListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                //判断当前的布局范围是否是在顶部布局的滑动范围内
                if (t <= DensityUtil.dip2px(getApplicationContext(), TOP_DISTANCE_Y)) {
                    isInTopDistance = true;
                } else {
                    isInTopDistance = false;
                }
                if (t <= DensityUtil.dip2px(getApplicationContext(), TOP_DISTANCE_Y) && isTopHide) {
                    showTop();
                } else if (t > DensityUtil.dip2px(getApplicationContext(), TOP_DISTANCE_Y) && !isTopHide) {
                    hideTop();
                }
            }
        });
        mScroller.setOnTouchListener(new View.OnTouchListener() {

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
                                if (!isToolHide)
                                    hideTool();
                            } else {
                                if (isToolHide)
                                    showTool();
                            }
                        }

                        lastY = event.getY();
                        break;
                }

                mGestureDetector.onTouchEvent(event);

                return false;
            }


        });
    }

    /**
     * 显示工具栏
     */
    private void showTool() {
        int startY = getWindow().getDecorView()
                .getHeight() - EaseAnimationUtils.getStatusHeight(this);
        ObjectAnimator anim = ObjectAnimator.ofFloat(bottom_tool, "y", startY,
                startY - bottom_tool.getHeight());
        anim.setDuration(TIME_ANIMATION);
        anim.start();
        isToolHide = false;

    }

    /**
     * 隐藏工具栏
     */
    private void hideTool() {
        int startY = getWindow().getDecorView()
                .getHeight() - EaseAnimationUtils.getStatusHeight(this);
        ObjectAnimator anim = ObjectAnimator.ofFloat(bottom_tool, "y", startY - bottom_tool.getHeight(),
                startY);
        anim.setDuration(TIME_ANIMATION);
        anim.start();
        isToolHide = true;
    }

    /**
     * 显示上部的布局
     */
    private void showTop() {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(title_bar, "y", title_bar.getY(),
                0);
        anim1.setDuration(TIME_ANIMATION);
        anim1.start();
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(tv_question_describe, "y", tv_question_describe.getY(),
                title_bar.getHeight());
        anim2.setInterpolator(new DecelerateInterpolator());
        anim2.setDuration(TIME_ANIMATION + 200);
        anim2.start();
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(fl_top, "y", fl_top.getY(),
                0);
        anim4.setDuration(TIME_ANIMATION);
        anim4.start();
        isTopHide = false;
    }


    /**
     * 隐藏上部的布局
     */
    private void hideTop() {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(title_bar, "y", 0,
                -title_bar.getHeight());
        anim1.setDuration(TIME_ANIMATION);
        anim1.start();
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(tv_question_describe, "y", tv_question_describe.getY(),
                -tv_question_describe.getHeight());
        anim2.setDuration(TIME_ANIMATION);
        anim2.start();
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(fl_top, "y", 0,
                -(title_bar.getHeight() + tv_question_describe.getHeight()));
        anim4.setDuration(TIME_ANIMATION);
        anim4.start();
        isTopHide = true;
    }


    /**
     * 手势指示器
     */
    private class DetailGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }


        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {

            //如果都是隐藏状态，那么都显示出来
            if (isTopHide && isToolHide) {
                showTop();
                showTool();
            } else if (!isToolHide && isTopHide) {
                //如果上面隐藏，下面显示，就显示上面
                showTop();
            } else if (!isTopHide && isToolHide) {
                //如果上面显示，下面隐藏，那么就显示下面
                showTool();
            } else {
                //都在显示，那么就都隐藏
                hideTool();
                if (!isInTopDistance) {
                    hideTop();
                }
            }
            return super.onSingleTapConfirmed(e);
        }
    }


}
