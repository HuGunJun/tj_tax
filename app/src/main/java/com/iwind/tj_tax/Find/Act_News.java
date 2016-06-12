package com.iwind.tj_tax.Find;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.widget.EaseTitleBar;
import com.easemob.easeui.widget.switchview.SegmentView;
import com.iwind.adapter.ViewPagerAdapter;
import com.iwind.tj_tax.Fragment.Frag_Hot;
import com.iwind.tj_tax.Fragment.Frag_Recommend;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 资讯页面
 * 作者：HuGuoJun
 * 2016/6/12 10:43
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_News extends EaseBaseActivity {

    @ViewInject(R.id.title_bar)
    EaseTitleBar title_bar;
    @ViewInject(R.id.vp_news)
    ViewPager vp_news;

    private List<Fragment> list_frag = new ArrayList<Fragment>();
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.act_news);
        ViewUtils.inject(this);
        InitView();
        InitData();
        setOnClickListener();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void InitView() {
        title_bar.setLeftImageResource(R.drawable.ease_mm_title_back);
        list_frag.add(new Frag_Hot());
        list_frag.add(new Frag_Recommend());
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list_frag);
        vp_news.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void InitData() {

    }

    @Override
    public void setOnClickListener() {
        title_bar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_bar.setSegmentViewIndexChangedListener(new SegmentView.OnIndexChangedListener() {
            @Override
            public void onChanged(SegmentView view, int index) {
                vp_news.setCurrentItem(index);
            }
        });
        vp_news.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                title_bar.setSegmentViewIndex(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
