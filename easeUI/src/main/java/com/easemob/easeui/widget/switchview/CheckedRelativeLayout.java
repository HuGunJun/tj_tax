package com.easemob.easeui.widget.switchview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.RelativeLayout;

import com.easemob.easeui.R;

public class CheckedRelativeLayout extends RelativeLayout implements Checkable {
	public static final int CHECKMODE_NONE = -1;
	public static final int CHECKMODE_RADIO = 0;
	public static final int CHECKMODE_CHECK = 1;
	public static final int[] CHECKED_STATE_SET = { android.R.attr.state_checked };

	protected boolean checked;
	protected int checkMode;
	protected boolean broadcasting;
	protected OnCheckedChangeListener onCheckedChangeListener;

	public CheckedRelativeLayout(Context context) {
		super(context);
		initialize();
	}

	public CheckedRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
		initFromAttributes(context, attrs);
	}

	protected void initialize() {
		setClickable(true);
	}

	protected void initFromAttributes(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CheckedRelativeLayout);
		checked = a.getBoolean(R.styleable.CheckedRelativeLayout_checked, false);
		checkMode = a.getInt(R.styleable.CheckedRelativeLayout_checkmode, CHECKMODE_RADIO);
		setChecked(checked);
		a.recycle();
	}

	public void setCheckMode(int checkMode) {
		this.checkMode = checkMode;
	}

	@Override
	public boolean performClick() {
		if (checkMode == CHECKMODE_CHECK) {
			toggle();
		} else if (checkMode == CHECKMODE_RADIO) {
			setChecked(true);
		}
		return super.performClick();
	}

	@Override
	public void setChecked(boolean checked) {
		if (this.checked != checked) {
			this.checked = checked;
			refreshDrawableState();

			if (broadcasting) {
				return;
			}

			broadcasting = true;
			if (onCheckedChangeListener != null) {
				onCheckedChangeListener.onCheckedChanged(this, checked);
			}

			broadcasting = false;
		}
	}

	@Override
	public boolean isChecked() {
		return checked;
	}

	@Override
	public void toggle() {
		setChecked(!checked);
	}

	@Override
	public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
		super.onInitializeAccessibilityEvent(event);
		event.setClassName(CheckedRelativeLayout.class.getName());
		event.setChecked(checked);
	}

	@Override
	public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
		super.onInitializeAccessibilityNodeInfo(info);
		info.setClassName(CheckedRelativeLayout.class.getName());
		info.setCheckable(true);
		info.setChecked(checked);
	}

	@Override
	public int[] onCreateDrawableState(int extraSpace) {
		int[] states = super.onCreateDrawableState(extraSpace + 1);
		if (isChecked()) {
			mergeDrawableStates(states, CHECKED_STATE_SET);
		}
		return states;
	}

	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();
		Drawable drawable = getBackground();
		if (drawable != null) {
			int[] myDrawableState = getDrawableState();
			drawable.setState(myDrawableState);
			invalidate();
		}
	}

	static class SavedState extends BaseSavedState {
		boolean checked;

		SavedState(Parcelable superState) {
			super(superState);
		}

		private SavedState(Parcel in) {
			super(in);
			checked = (Boolean) in.readValue(null);
		}

		@Override
		public void writeToParcel(Parcel out, int flags) {
			super.writeToParcel(out, flags);
			out.writeValue(checked);
		}

		@Override
		public String toString() {
			return "CompoundButton.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " checked=" + checked + "}";
		}

		public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
			public SavedState createFromParcel(Parcel in) {
				return new SavedState(in);
			}

			public SavedState[] newArray(int size) {
				return new SavedState[size];
			}
		};
	}

	@Override
	public Parcelable onSaveInstanceState() {
		Parcelable superState = super.onSaveInstanceState();

		SavedState ss = new SavedState(superState);

		ss.checked = isChecked();
		return ss;
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		SavedState ss = (SavedState) state;

		super.onRestoreInstanceState(ss.getSuperState());
		setChecked(ss.checked);
		requestLayout();
	}

	public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
		this.onCheckedChangeListener = onCheckedChangeListener;
	}

	public static interface OnCheckedChangeListener {
		public void onCheckedChanged(CheckedRelativeLayout layout, boolean isChecked);
	}
}
