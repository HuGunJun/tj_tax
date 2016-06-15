package com.iwind.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * User: HuGuoJun
 * Date: 2016-06-15
 * Time: 21:47
 * Email: www.guojunkuaile@qq.com
 * QQ: 1397883456
 */
public class MyScrollView extends ScrollView {
    private BottomListener bottomListener;

    private onScrollListener scrollListener;


    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (getScrollY() + getHeight() >= computeVerticalScrollRange()) {

            if (null != bottomListener) {
                bottomListener.onBottom();
            }

        }

        if (null != scrollListener) {
            scrollListener.onScrollChanged(l, t, oldl, oldt);
        }


    }

    public void setBottomListener(BottomListener bottomListener) {
        this.bottomListener = bottomListener;
    }

    public void setScrollListener(onScrollListener scrollListener) {

        this.scrollListener = scrollListener;

    }


    public interface onScrollListener {

        public void onScrollChanged(int l, int t, int oldl, int oldt);

    }


    public interface BottomListener {

        public void onBottom();

    }


}