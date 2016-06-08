package com.easemob.easeui.widget.viewpageranimation;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * 作者：HuGuoJun
 * 2016/5/18 10:59
 * 邮箱：www.guojunkuaile@qq.com
 */
public class TransformerForViewPager_four implements ViewPager.PageTransformer {
	private static final float MIN_SCALE = 0.75f;

	public void transformPage(View view, float position) {
		int pageWidth = view.getWidth();
		Log.i("TAG", "view = " + view + ",position = " + position);
		if (position < -1) {
			ViewHelper.setAlpha(view, 0);
		} else if (position <= 0) {
			ViewHelper.setAlpha(view, 1);
			ViewHelper.setTranslationX(view, 0);
			ViewHelper.setScaleX(view, 1);
			ViewHelper.setScaleY(view, 1);
		} else if (position <= 1) {
			ViewHelper.setAlpha(view, 1 - position);
			ViewHelper.setTranslationX(view, pageWidth * -position);
			float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)
					* (1 - Math.abs(position));
			ViewHelper.setScaleX(view, scaleFactor);
			ViewHelper.setScaleY(view, scaleFactor);
		} else {
			ViewHelper.setAlpha(view, 0);
		}
	}
}