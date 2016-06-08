package com.easemob.easeui.widget.progress;

import android.view.Gravity;

import com.easemob.easeui.R;

/**
 * 作者：HuGuoJun
 * 2016/5/18 10:14
 * 邮箱：www.guojunkuaile@qq.com
 */
public class SVProgressHUDAnimateUtil {
    private static final int INVALID = -1;

    static int getAnimationResource(int gravity, boolean isInAnimation) {
        switch (gravity) {
            case Gravity.TOP:
                return isInAnimation ? R.anim.svslide_in_top : R.anim.svslide_out_top;
            case Gravity.BOTTOM:
                return isInAnimation ? R.anim.svslide_in_bottom : R.anim.svslide_out_bottom;
            case Gravity.CENTER:
                return isInAnimation ? R.anim.svfade_in_center : R.anim.svfade_out_center;
            default:
                // This case is not implemented because we don't expect any other gravity at the moment
        }
        return INVALID;
    }
}
