package com.easemob.easeui.widget.videoview;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * 作者：HuGuoJun
 * 2016/6/1 09:01
 * 邮箱：www.guojunkuaile@qq.com
 */
public class SuperVideoView extends VideoView {

    private int videoWidth;
    private int videoHeight;

    public SuperVideoView(Context context) {
        super(context);
    }

    public SuperVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // @Override
    // protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    // Log.e("xiongwei","call onMeasure");
    // int width = getDefaultSize(videoWidth, widthMeasureSpec);
    // int height = getDefaultSize(videoHeight, heightMeasureSpec);
    // if (videoWidth > 0 && videoHeight > 0) {
    // if (videoWidth * height > width * videoHeight) {
    // height = width * videoHeight / videoWidth;
    // } else if (videoWidth * height < width * videoHeight) {
    // width = height * videoWidth / videoHeight;
    // }
    // }
    // setMeasuredDimension(width, height);
    // }

    public void setVideoWidth(int videoWidth) {
        this.videoWidth = videoWidth;
    }

    public void setVideoHeight(int videoHeight) {
        this.videoHeight = videoHeight;
    }

}
