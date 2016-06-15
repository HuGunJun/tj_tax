package com.easemob.easeui.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * 作者：HuGuoJun
 * 2016/6/15 17:28
 * 邮箱：www.guojunkuaile@qq.com
 */
public class EaseAnimationUtils {

    /**
     * ScrollView 滑动特效
     *
     * @param tag
     * @param viewTarget
     * @param scrollView
     */
    public static void showHideTitleBar(boolean tag, View viewTarget, ScrollView scrollView) {
        Animator mAnimatorTitle = null;
        Animator mAnimatorContent = null;
        if (mAnimatorTitle != null && mAnimatorTitle.isRunning()) {
            mAnimatorTitle.cancel();
        }
        if (mAnimatorContent != null && mAnimatorContent.isRunning()) {
            mAnimatorContent.cancel();
        }
        if (tag) {
            mAnimatorTitle = ObjectAnimator.ofFloat(viewTarget, "translationY", viewTarget.getTranslationY(), 0);
            mAnimatorContent = ObjectAnimator.ofFloat(scrollView, "translationY", scrollView.getTranslationY(), 0);
        } else {
            mAnimatorTitle = ObjectAnimator.ofFloat(viewTarget, "translationY", viewTarget.getTranslationY(), -viewTarget.getHeight());
            mAnimatorContent = ObjectAnimator.ofFloat(scrollView, "translationY", scrollView.getTranslationY(), 0);
        }
        mAnimatorTitle.start();
        mAnimatorContent.start();
    }

    /**
     * ListView 滑动特效
     *
     * @param tag
     * @param viewTarget
     * @param listview
     */
    public static void showHideTitleBar(boolean tag, View viewTarget, ListView listview) {
        Animator mAnimatorTitle = null;
        Animator mAnimatorContent = null;
        if (mAnimatorTitle != null && mAnimatorTitle.isRunning()) {
            mAnimatorTitle.cancel();
        }
        if (mAnimatorContent != null && mAnimatorContent.isRunning()) {
            mAnimatorContent.cancel();
        }
        if (tag) {
            mAnimatorTitle = ObjectAnimator.ofFloat(viewTarget, "translationY", viewTarget.getTranslationY(), 0);
            mAnimatorContent = ObjectAnimator.ofFloat(listview, "translationY", listview.getTranslationY(), 0);
        } else {
            mAnimatorTitle = ObjectAnimator.ofFloat(viewTarget, "translationY", viewTarget.getTranslationY(), -viewTarget.getHeight());
            mAnimatorContent = ObjectAnimator.ofFloat(listview, "translationY", listview.getTranslationY(), 0);
        }
        mAnimatorTitle.start();
        mAnimatorContent.start();

    }


    /**
     * 获取当前页面的高度状态
     * @param activity
     * @return
     */
    public static int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }


}
