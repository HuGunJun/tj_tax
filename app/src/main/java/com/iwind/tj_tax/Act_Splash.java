package com.iwind.tj_tax;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.iwind.App.MyApplication;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 作者：HuGuoJun
 * 2016/6/2 10:35
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_Splash extends EaseBaseActivity {

    @ViewInject(R.id.iv_splash)
    ImageView iv_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__splash);
        ViewUtils.inject(this);
        InitView();
    }

    @Override
    public void InitView() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(iv_splash, "scaleX", 1f, 1.13F);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(iv_splash, "scaleY", 1f, 1.13F);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000).play(animatorX).with(animatorY);
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!MyApplication.getInstance().getUserName().equals("")) {
                            startActivity(new Intent(context, MainActivity.class));
                            finish();
                        } else {
                            startActivity(new Intent(context, Act_Login.class));
                            finish();
                        }
                    }
                }, 3000);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public void InitData() {

    }

    @Override
    public void setOnClickListener() {

    }

    @Override
    public void onClick(View view) {

    }
}
