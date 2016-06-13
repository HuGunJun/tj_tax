package com.iwind.tj_tax.Mine;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.widget.EaseTitleBar;
import com.easemob.easeui.widget.xlistview.XListView;
import com.iwind.Constant.ConstantString;
import com.iwind.adapter.MessageAdapter;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 消息页面
 * 作者：HuGuoJun
 * 2016/6/13 08:49
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_Message extends EaseBaseActivity {
    @ViewInject(R.id.title_bar)
    EaseTitleBar title_bar;
    @ViewInject(R.id.lv_message)
    XListView lv_message;

    private List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    private MessageAdapter mMessageAdapter;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.act_message);
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
        title_bar.setTitle(getResources().getString(R.string.message));
        title_bar.setLeftImageResource(R.drawable.ease_mm_title_back);
    }

    @Override
    public void InitData() {

        for (int i = 0; i < 5; i++) {
            HashMap<String, String> hashMap = new HashMap<>();

            hashMap.put(ConstantString.TITLE, "系统通知");
            hashMap.put(ConstantString.CONTENT, "呵呵  我都不知道我在做社么，你个瓜孙");
            hashMap.put(ConstantString.TIME, "今天上午");
            list.add(hashMap);
        }
        mMessageAdapter=new MessageAdapter(context,list);
        lv_message.setAdapter(mMessageAdapter);
        mMessageAdapter.notifyDataSetChanged();

    }

    @Override
    public void setOnClickListener() {
        title_bar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
