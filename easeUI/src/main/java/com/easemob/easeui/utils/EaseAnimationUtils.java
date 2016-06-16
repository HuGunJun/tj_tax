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
    //默认的动画时间
    private static final int TIME_ANIMATION = 300;
    //工具栏是否是隐藏状态
    private static boolean isToolHide;


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
     *
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

    /**
     * 传送参数注释
     * windowHeight参数是当前窗口的高度
     *
     * @param widowHeight //Activity.getWindow().getDecorView() .getHeight()
     * @param view
     */
    public static void showTool(int widowHeight, View view, Activity activity) {
        int startY = widowHeight - EaseAnimationUtils.getStatusHeight(activity);
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "y", startY,
                startY - view.getHeight());
        anim.setDuration(TIME_ANIMATION);
        anim.start();
        isToolHide = false;
    }

    /**
     * 获取当前工具栏是否是隐藏
     *
     * @return
     */
    public static boolean isToolHide() {
        return isToolHide;
    }

    /**
     * 传送参数注释
     * windowHeight参数是当前窗口的高度
     *
     * @param widowHeight //Activity.getWindow().getDecorView() .getHeight()
     * @param view
     */
    public static void hideTool(int widowHeight, View view, Activity activity) {
        int startY = widowHeight - EaseAnimationUtils.getStatusHeight(activity);
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "y", startY - view.getHeight(),
                startY);
        anim.setDuration(TIME_ANIMATION);
        anim.start();
        isToolHide = true;
    }

}
