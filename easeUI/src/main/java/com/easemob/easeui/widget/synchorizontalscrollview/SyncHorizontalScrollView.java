package com.easemob.easeui.widget.synchorizontalscrollview;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.easemob.easeui.R;

import java.util.ArrayList;

/**
 * 作者：HuGuoJun
 * 2016/5/18 13:16
 * 邮箱：www.guojunkuaile@qq.com
 */

public class SyncHorizontalScrollView extends LinearLayout {
    private View mView;
    public BaseSynHorizontalScrollView mBaseSynHorizontalScrollView;
    private LayoutInflater mInflater;
    private ImageView leftImage;
    private ImageView rightImage;
    private Button btn_change;
    private BaseSynHorizontalScrollView.SynHorizontalScrollViewChangeListener mListener;

    public SyncHorizontalScrollView(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
        mInflater = LayoutInflater.from(context);

    }

    public SyncHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        mInflater = LayoutInflater.from(context);
    }

    /**
     * @param mContext
     * @param list
     * @param btnchange_text
     * @param view_pager
     * @param resouse
     */
    public void Init(Activity mContext, ArrayList<String> list, String btnchange_text, ViewPager view_pager, int resouse,
                     final BaseSynHorizontalScrollView.SynHorizontalScrollViewChangeListener mListener) {
        mView = mInflater.inflate(R.layout.include_horizantal_top_menu, null);
        mBaseSynHorizontalScrollView = (BaseSynHorizontalScrollView) mView.findViewById(R.id.mHsv);
        leftImage = (ImageView) mView.findViewById(R.id.iv_nav_left);
        rightImage = (ImageView) mView.findViewById(R.id.iv_nav_right);
        btn_change = (Button) mView.findViewById(R.id.btn_change);
        mBaseSynHorizontalScrollView.SetParams(leftImage, rightImage, btn_change, mContext, list, resouse);
        setBtnChangeText(btnchange_text);
        setViewPager(view_pager);
        setBtnChangeVisiable(btnchange_text == null ? false : true);
        btn_change.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.ChangeListener();
            }
        });
        this.addView(mView);
    }

    /**
     * 设置修改按钮提示文字
     *
     * @param test
     */
    public void setBtnChangeText(String test) {
        btn_change.setText(test);
    }


    /**
     * 设置修改按钮是否可见
     *
     * @param visiable
     */
    public void setBtnChangeVisiable(boolean visiable) {
        btn_change.setVisibility(visiable ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置默认选择id
     *
     * @param id
     */
    public void setCheck(int id) {
        mBaseSynHorizontalScrollView.setCheck(id);
    }


    /**
     * 设置被监听的ViewPager
     *
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager) {
        mBaseSynHorizontalScrollView.setViewPager(viewPager);
    }

    /**
     * 设置当前选中项
     *
     * @param position
     */
    public void setCurrentIndicator(int position) {
        mBaseSynHorizontalScrollView.setCurrentIndicator(position);
    }

}
