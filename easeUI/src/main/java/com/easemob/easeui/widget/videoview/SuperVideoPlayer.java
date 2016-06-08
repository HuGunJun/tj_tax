package com.easemob.easeui.widget.videoview;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.easemob.easeui.R;
import com.easemob.easeui.widget.barrageview.BarrageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 作者：HuGuoJun
 * 2016/6/1 09:00
 * 邮箱：www.guojunkuaile@qq.com
 */
public class SuperVideoPlayer extends RelativeLayout {
    private final int TIME_SHOW_CONTROLLER = 3000;
    private final int TIME_UPDATE_PLAY_TIME = 1000;

    private final int MSG_HIDE_CONTROLLER = 10;
    private final int MSG_UPDATE_PLAY_TIME = 11;
    private MediaController.PageType mCurrPageType = MediaController.PageType.SHRINK;// 当前是横屏还是竖屏

    private Context mContext;
    private SuperVideoView mSuperVideoView;
    private MediaController mMediaController;
    private Timer mUpdateTimer;
    private VideoPlayCallbackImpl mVideoPlayCallback;

    private View mProgressBarView;
    private ArrayList<Video> mAllVideo;
    private Video mNowPlayVideo;
    private BarrageView barrageview;


    /**
     * 通知更新进程和时间
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_UPDATE_PLAY_TIME) {
                updatePlayTime();
                updatePlayProgress();
            } else if (msg.what == MSG_HIDE_CONTROLLER) {
                showOrHideController();
            }
        }
    };


    private View.OnTouchListener mOnTouchVideoListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                showOrHideController();
            }
            return mCurrPageType == MediaController.PageType.EXPAND ? true
                    : false;
        }
    };


    private MediaController.MediaControlImpl mMediaControl = new MediaController.MediaControlImpl() {
        @Override
        public void alwaysShowController() {
            SuperVideoPlayer.this.alwaysShowController();
        }

        @Override
        public void onSelectSrc(int position) {
            Video selectVideo = mAllVideo.get(position);
            if (selectVideo.equal(mNowPlayVideo))
                return;
            mNowPlayVideo = selectVideo;
            mNowPlayVideo.setPlayUrl(0);
            mMediaController.initPlayVideo(mNowPlayVideo);
            playVideoFromStart();
        }

        @Override
        public void onSelectFormat(int position) {
            VideoUrl videoUrl = mNowPlayVideo.getVideoUrl().get(position);
            if (mNowPlayVideo.getPlayUrl().equal(videoUrl))
                return;
            mNowPlayVideo.setPlayUrl(position);
            playVideoAtLastPos();
        }

        @Override
        public void onPlayTurn() {
            if (mSuperVideoView.isPlaying()) {
                pausePlay();
            } else {
                startPlayVideo(0);
            }
        }

        @Override
        public void onPageTurn() {
            mVideoPlayCallback.onSwitchPageType();
        }

        @Override
        public void onProgressTurn(MediaController.ProgressState state,
                                   int progress) {
            if (state.equals(MediaController.ProgressState.START)) {
                mHandler.removeMessages(MSG_HIDE_CONTROLLER);
            } else if (state.equals(MediaController.ProgressState.STOP)) {
                resetHideTimer();
            } else {
                int time = progress * mSuperVideoView.getDuration() / 100;
                mSuperVideoView.seekTo(time);
                updatePlayTime();
            }
        }
    };

    private MediaPlayer.OnPreparedListener mOnPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                        mProgressBarView.setVisibility(View.GONE);
                        return true;
                    }
                    return false;
                }
            });

            mSuperVideoView.setVideoWidth(mediaPlayer.getVideoWidth());
            mSuperVideoView.setVideoHeight(mediaPlayer.getVideoHeight());
        }
    };

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            stopUpdateTimer();
            stopHideTimer();
            mMediaController.playFinish(mSuperVideoView.getDuration());
            mVideoPlayCallback.onPlayFinish();
        }
    };

    /***
     * 如果在地图页播放视频，请先调用该接口
     */
    public void setSupportPlayOnSurfaceView() {
        mSuperVideoView.setZOrderMediaOverlay(true);
    }

    public SuperVideoView getSuperVideoView() {
        return mSuperVideoView;
    }

    public void setPageType(MediaController.PageType pageType) {
        mMediaController.setPageType(pageType);
        mCurrPageType = pageType;
    }

    /***
     * 播放多个视频
     *
     * @param allVideo
     */
    public void loadMultipleVideo(ArrayList<Video> allVideo) {
        if (null == allVideo || allVideo.size() == 0) {
            Toast.makeText(mContext, "视频列表为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mAllVideo.clear();
        mAllVideo.addAll(allVideo);
        mNowPlayVideo = mAllVideo.get(0);
        mNowPlayVideo.setPlayUrl(0);
        mMediaController.initVideoList(mAllVideo);
        mMediaController.initPlayVideo(mNowPlayVideo);
        playVideoFromStart();
    }

    public void setVideoPlayCallback(VideoPlayCallbackImpl videoPlayCallback) {
        mVideoPlayCallback = videoPlayCallback;
    }

    public void pausePlay() {
        mSuperVideoView.pause();
        mMediaController.setPlayState(MediaController.PlayState.PAUSE);
        stopHideTimer();
    }

    public void stopPlay() {
        pausePlay();
        stopUpdateTimer();
    }

    public void close() {
        mMediaController.setPlayState(MediaController.PlayState.PAUSE);
        stopHideTimer();
        stopUpdateTimer();
        mSuperVideoView.stopPlayback();
        mSuperVideoView.setVisibility(GONE);
    }

    public SuperVideoPlayer(Context context) {
        super(context);
        initView(context);
    }

    public SuperVideoPlayer(Context context, AttributeSet attrs,
                            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public SuperVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View.inflate(context, R.layout.super_vodeo_player_layout, this);
        mSuperVideoView = (SuperVideoView) findViewById(R.id.video_view);
        mMediaController = (MediaController) findViewById(R.id.controller);
        barrageview = (BarrageView) findViewById(R.id.barrageview);
        mProgressBarView = findViewById(R.id.progressbar);
        mMediaController.setMediaControl(mMediaControl);
        mSuperVideoView.setOnTouchListener(mOnTouchVideoListener);

        mProgressBarView.setVisibility(VISIBLE);

        mAllVideo = new ArrayList<>();
    }


    /***
     * 更换清晰度地址时，续播
     */
    private void playVideoAtLastPos() {
        int playTime = mSuperVideoView.getCurrentPosition();
        loadAndPlay(mNowPlayVideo.getPlayUrl().getFormatUrl(), playTime);
    }

    /***
     * 从头播放视频
     */
    public void playVideoFromStart() {
        loadAndPlay(mNowPlayVideo.getPlayUrl().getFormatUrl(), 0);
    }

    /***
     * 加载视频
     *
     * @param videoUrl
     */
    private void loadVideo(String videoUrl) {
        if (TextUtils.isEmpty(videoUrl)) {
            Log.e("TAG", "videoUrl should not be null");
            return;
        }
        resetUpdateTimer();
        mSuperVideoView.setOnPreparedListener(mOnPreparedListener);
        mSuperVideoView.setVideoPath(videoUrl);
        mSuperVideoView.setVisibility(VISIBLE);
    }

    /***
     * 加载并开始播放视频
     *
     * @param videoUrl
     */
    public void loadAndPlay(String videoUrl, int seekTime) {
        mProgressBarView.setVisibility(VISIBLE);
        if (seekTime == 0) {
            mProgressBarView.setBackgroundResource(android.R.color.black);
        } else {
            mProgressBarView.setBackgroundResource(android.R.color.transparent);
        }
        loadVideo(videoUrl);
        startPlayVideo(seekTime);
    }

    /**
     * 播放视频 should called after loadVideo()
     */
    private void startPlayVideo(int seekTime) {
        if (null == mUpdateTimer)
            resetUpdateTimer();
        resetHideTimer();
        mSuperVideoView.setOnCompletionListener(mOnCompletionListener);
        mSuperVideoView.start();
        if (seekTime > 0) {
            mSuperVideoView.seekTo(seekTime);
        }
        mMediaController.setPlayState(MediaController.PlayState.PLAY);
    }

    /**
     * 更新播放时间
     */
    private void updatePlayTime() {
        int allTime = mSuperVideoView.getDuration();
        int playTime = mSuperVideoView.getCurrentPosition();
        mMediaController.setPlayProgressTxt(playTime, allTime);
    }

    /**
     * 更新播放进度
     */
    private void updatePlayProgress() {
        int allTime = mSuperVideoView.getDuration();
        int playTime = mSuperVideoView.getCurrentPosition();
        int loadProgress = mSuperVideoView.getBufferPercentage();
        int progress = playTime * 100 / allTime;
        mMediaController.setProgressBar(progress, loadProgress);
    }


    /***
     * 显示或者隐藏控制器
     */
    private void showOrHideController() {
        mMediaController.closeAllSwitchList();
        if (mMediaController.getVisibility() == View.VISIBLE) {
            Animation animation = AnimationUtils.loadAnimation(mContext,
                    R.anim.anim_exit_from_bottom);
            animation.setAnimationListener(new AnimationImp() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    super.onAnimationEnd(animation);
                    mMediaController.setVisibility(View.GONE);
                }
            });
            mMediaController.startAnimation(animation);
        } else {
            mMediaController.setVisibility(View.VISIBLE);
            mMediaController.clearAnimation();
            Animation animation = AnimationUtils.loadAnimation(mContext,
                    R.anim.anim_enter_from_bottom);
            mMediaController.startAnimation(animation);
            resetHideTimer();
        }
    }

    /**
     * 保持显示控制器
     */
    private void alwaysShowController() {
        mHandler.removeMessages(MSG_HIDE_CONTROLLER);
        mMediaController.setVisibility(View.VISIBLE);
    }

    private void resetHideTimer() {
        mHandler.removeMessages(MSG_HIDE_CONTROLLER);
        mHandler.sendEmptyMessageDelayed(MSG_HIDE_CONTROLLER,
                TIME_SHOW_CONTROLLER);
    }

    /**
     * 停止更新时间
     */
    private void stopHideTimer() {
        mHandler.removeMessages(MSG_HIDE_CONTROLLER);
        mMediaController.setVisibility(View.VISIBLE);
        mMediaController.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(mContext,
                R.anim.anim_enter_from_bottom);
        mMediaController.startAnimation(animation);
    }

    /**
     * 重置时间
     */
    private void resetUpdateTimer() {
        mUpdateTimer = new Timer();
        mUpdateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(MSG_UPDATE_PLAY_TIME);
            }
        }, 0, TIME_UPDATE_PLAY_TIME);
    }

    /**
     * 停止更新时间控件
     */
    private void stopUpdateTimer() {
        if (mUpdateTimer != null) {
            mUpdateTimer.cancel();
            mUpdateTimer = null;
        }
    }


    private class AnimationImp implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }
    }

    public interface VideoPlayCallbackImpl {
        void onCloseVideo();

        void onSwitchPageType();

        void onPlayFinish();
    }

}
