package com.easemob.easeui.widget.switchview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.easemob.easeui.R;

public class SwitchView extends FrameLayout {
	protected boolean isChecked;
	protected View onBgView;
	protected View offBgView;
	protected View circleView;
	protected boolean autoForPerformClick = true;
	protected OnCheckedChangedListener onCheckedChangedListener;

	public SwitchView(Context context) {
		super(context);
		initialize();
	}

	public SwitchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	public SwitchView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
	}

	protected void initialize() {
		setClickable(true);
		LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.ease_switch_view, this);
		onBgView = findViewById(R.id.on_bg_view);
		offBgView = findViewById(R.id.off_bg_view);
		circleView = findViewById(R.id.circle_view);
	}

	protected void setText(String onText, String offText) {

	}

	public void setAutoForPerformClick(boolean autoForPerformClick) {
		this.autoForPerformClick = autoForPerformClick;
	}

	@Override
	public boolean performClick() {
		if (!autoForPerformClick)
			return super.performClick();
		setChecked(!isChecked, true);
		if (onCheckedChangedListener != null) {
			onCheckedChangedListener.onChanged(this, isChecked);
		}
		return super.performClick();
	}

	public void setChecked(boolean value) {
		setChecked(value, true);
	}

	public void setChecked(boolean value, boolean needAnimate) {
		if (isChecked == value)
			return;
		isChecked = value;

		float targetX = 0;
		if (getWidth() != 0) {
			targetX = getWidth() - circleView.getWidth();
		} else {
			measure(0, 0);
			targetX = getMeasuredWidth() - circleView.getMeasuredWidth();
		}

		long durationMillis = needAnimate ? 200 : 0;
		if (isChecked) {
			onBgView.bringToFront();
			onBgView.setVisibility(View.VISIBLE);
			offBgView.setVisibility(View.VISIBLE);

			TranslateAnimation an1 = new TranslateAnimation(0, targetX, 0, 0);
			an1.setFillAfter(true);
			an1.setDuration(durationMillis);
			circleView.startAnimation(an1);

			AlphaAnimation an2 = new AlphaAnimation(0, 1);
			an2.setFillAfter(true);
			an2.setDuration(durationMillis);
			onBgView.startAnimation(an2);
		} else {
			offBgView.bringToFront();
			onBgView.setVisibility(View.VISIBLE);
			offBgView.setVisibility(View.VISIBLE);

			TranslateAnimation an1 = new TranslateAnimation(targetX, 0, 0, 0);
			an1.setFillAfter(true);
			an1.setDuration(durationMillis);
			circleView.startAnimation(an1);

			AlphaAnimation an2 = new AlphaAnimation(0, 1);
			an2.setFillAfter(true);
			an2.setDuration(durationMillis);
			offBgView.startAnimation(an2);
		}
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setOnCheckedChangedListener(OnCheckedChangedListener l) {
		onCheckedChangedListener = l;
	}

	public interface OnCheckedChangedListener {
		void onChanged(View view, boolean checked);
	}
}
