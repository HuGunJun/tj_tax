package com.iwind.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easemob.easeui.utils.EaseUserUtils;
import com.iwind.Constant.ConstantString;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.List;

/**
 * 作者：HuGuoJun
 * 2016/6/7 10:21
 * 邮箱：www.guojunkuaile@qq.com
 */
public class HomePageAdapter extends BaseAdapter {

    private Context mContext;
    private List<HashMap<String, String>> mList;

    public HomePageAdapter(Context mContext, List<HashMap<String, String>> mapList) {
        this.mContext = mContext;
        this.mList = mapList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_homepage, null);
            ViewUtils.inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        EaseUserUtils.setUserAvatar(mContext, mList.get(position).get(ConstantString.IV_URL), holder.iv_avator);

        holder.tv_content.setText(mList.get(position).get(ConstantString.CONTENT));
        holder.tv_name.setText(mList.get(position).get(ConstantString.NAME));
        holder.tv_read_count.setText(mList.get(position).get(ConstantString.READ_COUNT));
        holder.tv_type.setText(mList.get(position).get(ConstantString.TYPE));
        return convertView;
    }


    private class ViewHolder {
        @ViewInject(R.id.tv_name)
        TextView tv_name;
        @ViewInject(R.id.iv_avator)
        ImageView iv_avator;
        @ViewInject(R.id.tv_content)
        TextView tv_content;
        @ViewInject(R.id.tv_read_count)
        TextView tv_read_count;
        @ViewInject(R.id.tv_type)
        TextView tv_type;
    }
}
