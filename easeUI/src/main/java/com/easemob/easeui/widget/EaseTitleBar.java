package com.easemob.easeui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easemob.easeui.R;
import com.easemob.easeui.widget.switchview.SegmentView;

/**
 * 标题栏
 */
public class EaseTitleBar extends RelativeLayout {

    protected RelativeLayout leftLayout;
    protected ImageView leftImage;
    protected RelativeLayout rightLayout;
    protected ImageView rightImage;
    protected TextView titleView;
    protected RelativeLayout titleLayout;
    protected RelativeLayout centerlayout;
    protected TextView tv_right;
    protected SegmentView segmentView;

    public EaseTitleBar(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public EaseTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EaseTitleBar(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.ease_widget_title_bar, this);
        leftLayout = (RelativeLayout) findViewById(R.id.left_layout);
        leftImage = (ImageView) findViewById(R.id.left_image);
        rightLayout = (RelativeLayout) findViewById(R.id.right_layout);
        rightImage = (ImageView) findViewById(R.id.right_image);
        titleView = (TextView) findViewById(R.id.title);
        titleLayout = (RelativeLayout) findViewById(R.id.root);
        centerlayout = (RelativeLayout) findViewById(R.id.center_layout);
        tv_right = (TextView) findViewById(R.id.tv_right);
        segmentView = (SegmentView) findViewById(R.id.segmentView);
        parseStyle(context, attrs);
    }

    private void parseStyle(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EaseTitleBar);
            String title = ta.getString(R.styleable.EaseTitleBar_titleBarTitle);
            titleView.setText(title);
            if (title != null) {
                segmentView.setVisibility(View.GONE);
            } else {
                segmentView.setVisibility(View.VISIBLE);
            }
            Drawable leftDrawable = ta.getDrawable(R.styleable.EaseTitleBar_titleBarLeftImage);
            if (null != leftDrawable) {
                leftImage.setImageDrawable(leftDrawable);
            }
            Drawable rightDrawable = ta.getDrawable(R.styleable.EaseTitleBar_titleBarRightImage);
            if (null != rightDrawable) {
                rightImage.setImageDrawable(rightDrawable);
            }

            Drawable background = ta.getDrawable(R.styleable.EaseTitleBar_titleBarBackground);
            if (null != background) {
                titleLayout.setBackgroundDrawable(background);
            }

            ta.recycle();
        }
    }

    public void setLeftImageResource(int resId) {
        leftImage.setImageResource(resId);
    }

    public void setRightImageResource(int resId) {
        rightImage.setImageResource(resId);
    }

    public void setRightImageClickListener(OnClickListener listener) {
        rightImage.setOnClickListener(listener);
    }


    public void setLeftLayoutClickListener(OnClickListener listener) {
        leftLayout.setOnClickListener(listener);
    }

    public void setRightLayoutClickListener(OnClickListener listener) {
        rightLayout.setOnClickListener(listener);
    }

    public void setLeftLayoutVisibility(int visibility) {
        leftLayout.setVisibility(visibility);
    }

    public void setRightLayoutVisibility(int visibility) {
        rightLayout.setVisibility(visibility);
    }

    public void setTitle(String title) {
        titleView.setText(title);
        if (!title.equals("") || title != null) {
            segmentView.setVisibility(View.GONE);
        } else {
            segmentView.setVisibility(View.VISIBLE);
        }
    }

    public void setBackgroundColor(int color) {
        titleLayout.setBackgroundColor(color);
    }

    public RelativeLayout getLeftLayout() {
        return leftLayout;
    }

    public RelativeLayout getRightLayout() {
        return rightLayout;
    }

    public void setCenterLayoutClickListener(OnClickListener listener) {
        centerlayout.setOnClickListener(listener);
    }


    public void setRightText(String rightText) {
        tv_right.setText(rightText);
    }

    public void setRightTextColor(int colorid) {
        tv_right.setTextColor(colorid);
    }

    public void setRightTextClickListener(OnClickListener listener) {
        tv_right.setOnClickListener(listener);
    }

    public void setSegmentViewIndexChangedListener(SegmentView.OnIndexChangedListener listener) {
        segmentView.setOnIndexChangedListener(listener);
    }

    public void setRightImageAndTextVisiable(boolean image_visiable, boolean text_visiable) {
        rightImage.setVisibility(image_visiable ? View.VISIBLE : View.GONE);
        tv_right.setVisibility(text_visiable ? View.VISIBLE : View.GONE);

    }


    public void setSegmentViewIndex(int index) {
        segmentView.setIndex(index);
    }
}
