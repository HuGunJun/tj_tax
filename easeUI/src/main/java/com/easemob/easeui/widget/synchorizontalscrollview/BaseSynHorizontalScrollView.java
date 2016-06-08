package com.easemob.easeui.widget.synchorizontalscrollview;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.easemob.easeui.R;

import java.util.ArrayList;

/**
 * 作者：HuGuoJun
 * 2016/5/18 16:47
 * 邮箱：www.guojunkuaile@qq.com
 */
public class BaseSynHorizontalScrollView extends HorizontalScrollView {

    private View view;
    private ImageView leftImage;
    private ImageView rightImage;
    private RadioGroup rg_nav_content;//组
    private ImageView iv_nav_indicator;//下标指示器
    private int indicatorWidth = 0;//每个指示器的宽度
    private int windowWitdh = 0;
    private int currentIndicatorLeft = 0;
    private Activity mContext;
    public Button btn_change;
    private LayoutInflater mInflater;
    private ViewPager vp;
    private int TextColor;//item选择颜色

    public BaseSynHorizontalScrollView(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
    }

    public BaseSynHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
    }

    public void SetParams(ImageView leftImage,
                          ImageView rightImage, Button btn_change, Activity context, ArrayList<String> list, int TextColor) {
        this.mContext = context;
        this.leftImage = leftImage;
        this.rightImage = rightImage;
        this.btn_change = btn_change;
        this.TextColor = TextColor;
        /** 获取屏幕大小 **/
        view = mInflater.inflate(R.layout.ease_base_sychronizhorizontalscrollview, null);
        this.addView(view);
        iv_nav_indicator = (ImageView) view.findViewById(R.id.iv_nav_indicator);
        rg_nav_content = (RadioGroup) view.findViewById(R.id.rg_nav_content);
        DisplayMetrics dm = new DisplayMetrics();
        this.mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
        windowWitdh = dm.widthPixels;
        indicatorWidth = dm.widthPixels / 4;
        ViewGroup.LayoutParams cursor_Params = iv_nav_indicator.getLayoutParams();
        cursor_Params.width = indicatorWidth;// 初始化滑动下标的宽
        iv_nav_indicator.setLayoutParams(cursor_Params);

        InitRadopGroup(list);
    }


    /**
     * 初始化菜单按钮
     *
     * @param list
     */
    private void InitRadopGroup(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            RadioButton rb = (RadioButton) mInflater.inflate(
                    R.layout.nav_radiogroup_item, null);
            rb.setId(i);
            rb.setTextColor(getResources().getColorStateList(TextColor));
            rb.setText(list.get(i));
            rb.setLayoutParams(new ViewGroup.LayoutParams(indicatorWidth,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            rg_nav_content.addView(rb);
        }
        rg_nav_content.check(0);
    }


    /**
     * 设置监听事件
     */
    private void InitListener() {
        rg_nav_content.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                if (rg_nav_content.getChildAt(checkedId) != null) {
                    TranslateAnimation animation = new TranslateAnimation(
                            currentIndicatorLeft,
                            ((RadioButton) rg_nav_content
                                    .getChildAt(checkedId)).getLeft(),
                            0f, 0f);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setDuration(100);
                    animation.setFillAfter(true);
                    // 执行位移动画
                    iv_nav_indicator.startAnimation(animation);
                    vp.setCurrentItem(checkedId); // ViewPager
                    // 跟随一起 切换
                    // 记录当前 下标的距最左侧的 距离
                    currentIndicatorLeft = ((RadioButton) rg_nav_content
                            .getChildAt(checkedId)).getLeft();
                    smoothScrollTo(
                            (checkedId > 1 ? ((RadioButton) rg_nav_content
                                    .getChildAt(checkedId)).getLeft()
                                    : 0)
                                    - ((RadioButton) rg_nav_content
                                    .getChildAt(1)).getLeft(),
                            0);
                }

            }
        });
    }

    /**
     * 滑动监听
     *
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (!mContext.isFinishing() && view != null && rightImage != null
                && leftImage != null) {
            if (view.getWidth() <= windowWitdh) {
                leftImage.setVisibility(View.GONE);
                rightImage.setVisibility(View.GONE);
            } else {
                if (l == 0) {
                    leftImage.setVisibility(View.GONE);
                    rightImage.setVisibility(View.VISIBLE);
                } else if (view.getWidth() - l == windowWitdh -
                        btn_change.getWidth()) {
                    leftImage.setVisibility(View.VISIBLE);
                    rightImage.setVisibility(View.GONE);
                } else {
                    leftImage.setVisibility(View.VISIBLE);
                    rightImage.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    /**
     * 设置默认选择
     *
     * @param id
     */
    public void setCheck(int id) {
        rg_nav_content.check(id - 1);
    }


    /**
     * 设置被监听的ViewPager
     *
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager) {
        this.vp = viewPager;
        InitListener();
    }

    public void setCurrentIndicator(int position) {
        if (rg_nav_content != null
                && rg_nav_content.getChildCount() > position) {
            ((RadioButton) rg_nav_content.getChildAt(position))
                    .performClick();
        }

    }

    public interface SynHorizontalScrollViewChangeListener {
        /**
         * 修改按钮接口
         */
        void ChangeListener();
    }
}
