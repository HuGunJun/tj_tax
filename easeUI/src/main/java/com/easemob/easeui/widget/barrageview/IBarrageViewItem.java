package com.easemob.easeui.widget.barrageview;

import android.graphics.Canvas;

/**
 * 作者：HuGuoJun
 * 2016/6/1 13:37
 * 邮箱：www.guojunkuaile@qq.com
 */

/**
 * 弹幕接口
 */
public interface IBarrageViewItem {
    void doDraw(Canvas canvas);

    void setTextSize(int sizeInDip);

    void setTextColor(int colorResId);

    void setStartPosition(int x, int y);

    void setSpeedFactor(float factor);

    float getSpeedFactor();

    boolean isOut();

    boolean willHit(IBarrageViewItem runningItem);

    void release();

    int getWidth();

    int getHeight();

    int getCurrX();

    int getCurrY();
}
