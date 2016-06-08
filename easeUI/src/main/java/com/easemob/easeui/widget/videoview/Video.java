package com.easemob.easeui.widget.videoview;

/**
 * 作者：HuGuoJun
 * 2016/6/1 08:56
 * 邮箱：www.guojunkuaile@qq.com
 */

import java.util.ArrayList;

/**
 *
 */
public class Video {

    private String mVideoName;// 视频名称
    private ArrayList<VideoUrl> mVideoUrl;// 视频的地址列表

    /*************** 请看注释 ***************************/
    private VideoUrl mPlayUrl;// 当前正在播放的地址。 外界不用传

    public String getVideoName() {
        return mVideoName;
    }

    public void setVideoName(String videoName) {
        mVideoName = videoName;
    }

    public ArrayList<VideoUrl> getVideoUrl() {
        return mVideoUrl;
    }

    public void setVideoUrl(ArrayList<VideoUrl> videoUrl) {
        mVideoUrl = videoUrl;
    }

    public VideoUrl getPlayUrl() {
        return mPlayUrl;
    }

    public void setPlayUrl(VideoUrl playUrl) {
        mPlayUrl = playUrl;
    }

    public void setPlayUrl(int position) {
        if (position < 0 || position >= mVideoUrl.size())
            return;
        setPlayUrl(mVideoUrl.get(position));
    }

    public boolean equal(Video video) {
        if (null != video) {
            return mVideoName.equals(video.getVideoName());
        }
        return false;
    }


}
