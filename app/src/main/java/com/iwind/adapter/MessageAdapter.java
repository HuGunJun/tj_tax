package com.iwind.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.List;

/**
 * 作者：HuGuoJun
 * 2016/6/13 08:53
 * 邮箱：www.guojunkuaile@qq.com
 */
public class MessageAdapter extends BaseAdapter {

    Context mContext;
    List<HashMap<String, String>> list;


    public MessageAdapter(Context context, List<HashMap<String, String>> list) {
        this.mContext = context;
        this.list
                = list;
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

        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_message,null);
            ViewUtils.inject(holder,convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }




        return convertView;
    }


    class ViewHolder {
        @ViewInject(R.id.tv_title)
        TextView tv_title;
        @ViewInject(R.id.tv_content)
        TextView tv_content;
        @ViewInject(R.id.tv_time)
        TextView tv_time;
    }
}
