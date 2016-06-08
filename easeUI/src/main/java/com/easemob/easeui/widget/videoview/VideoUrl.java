package com.easemob.easeui.widget.videoview;

/**
 * 作者：HuGuoJun
 * 2016/6/1 08:56
 * 邮箱：www.guojunkuaile@qq.com
 */

/**
 * 视频模型
 */
public class VideoUrl {

    private String mFormatName;// 视频格式名称，例如高清，标清，720P等等
    private String mFormatUrl;// 视频Url

    public String getFormatName() {
        return mFormatName;
    }

    public void setFormatName(String formatName) {
        mFormatName = formatName;
    }

    public String getFormatUrl() {
        return mFormatUrl;
    }

    public void setFormatUrl(String formatUrl) {
        mFormatUrl = formatUrl;
    }

    public boolean equal(VideoUrl url) {
        if (null != url) {
            return getFormatName().equals(url.getFormatName())
                    && getFormatUrl().equals(url.getFormatUrl());
        }
        return false;
    }


}
