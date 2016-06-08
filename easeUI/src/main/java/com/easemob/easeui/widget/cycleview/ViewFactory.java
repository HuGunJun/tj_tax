package com.easemob.easeui.widget.cycleview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.easemob.easeui.R;
import com.easemob.easeui.widget.cycleview.CycleViewPager.ImageCycleViewListener;


/**
 * 作者：HuGuoJun
 * 2016/5/18 11:11
 * 邮箱：www.guojunkuaile@qq.com
 */
public class ViewFactory {

    /**
     * 轮播广告图
     */
    public static void initialize(final Context context, View vhdf,
                                  final CycleViewPager cycleViewPager, List<CycleVpEntity> cycentiy,
                                  final ImageCycleViewListener listener) {

        List<CycleVpEntity> infos = new ArrayList<CycleVpEntity>();
        List<ImageView> views = new ArrayList<ImageView>();
        for (int i = 0; i < cycentiy.size(); i++) {
            CycleVpEntity info = new CycleVpEntity();
            info.setIurl(cycentiy.get(i).getIurl());
            info.setTitle(cycentiy.get(i).getTitle());
            info.setCurl(cycentiy.get(i).getCurl());
            infos.add(info);
        }
        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(context, infos.get(infos.size() - 1)
                .getIurl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(context, infos.get(i).getIurl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(context, infos.get(0).getIurl()));
        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);
        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, new ImageCycleViewListener() {

            @Override
            public void onImageClick(CycleVpEntity info, int postion,
                                     View imageView) {
                // TODO Auto-generated method stub
                if (cycleViewPager.isCycle()) {
                    postion = postion - 1;
                    listener.onImageClick(info, postion, imageView);
                }
            }
        });
        // 设置轮播
        cycleViewPager.setWheel(true);
        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(2000);
    }

    /**
     * 获取ImageView视图的同时加载显示url
     *
     * @param text
     * @return
     */
    public static ImageView getImageView(Context context, String url) {
        ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(
                R.layout.ease_view_banner, null);
        Glide.with(context).load(url).error(R.drawable.ease_default_image).into(imageView);
        // imageView.setImageBitmap(createVideoThumbnail(url, 100, 100));
        return imageView;
    }

    /**
     * 使用视频缩略图
     *
     * @param url
     * @param width
     * @param height
     * @return
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static Bitmap createVideoThumbnail(String url, int width, int height) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        int kind = MediaStore.Video.Thumbnails.MINI_KIND;
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                retriever.setDataSource(url, new HashMap<String, String>());
            } else {
                retriever.setDataSource(url);
            }
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
            }
        }
        if (kind == Images.Thumbnails.MICRO_KIND && bitmap != null) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }


}
