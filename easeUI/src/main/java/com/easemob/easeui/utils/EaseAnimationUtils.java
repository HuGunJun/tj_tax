package com.easemob.easeui.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ListView;

/**
 * 作者：HuGuoJun
 * 2016/6/15 17:28
 * 邮箱：www.guojunkuaile@qq.com
 */
public class EaseAnimationUtils {
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

}
