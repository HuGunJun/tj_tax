package com.iwind.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * 作者：HuGuoJun
 * 2016/6/12 11:57
 * 邮箱：www.guojunkuaile@qq.com
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list_frag;
    private FragmentManager fm;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list_frag) {
        super(fm);
        this.fm = fm;
        this.list_frag = list_frag;
    }

    @Override
    public Fragment getItem(int position) {
        return list_frag.get(position);
    }

    @Override
    public int getCount() {
        return list_frag.size();
    }
}
