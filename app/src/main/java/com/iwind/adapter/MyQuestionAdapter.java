package com.iwind.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;

import java.util.HashMap;
import java.util.List;

/**
 * 我的提问适配器
 * 作者：HuGuoJun
 * 2016/6/12 17:54
 * 邮箱：www.guojunkuaile@qq.com
 */
public class MyQuestionAdapter extends BaseAdapter {

    List<HashMap<String, String>> list;
    Context context;

    public MyQuestionAdapter(Context context, List<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_myquestion, null);
            ViewUtils.inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    private class ViewHolder {

    }
}
