package com.iwind.tj_tax.Mine;

import android.os.Bundle;
import android.view.View;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.widget.EaseTitleBar;
import com.easemob.easeui.widget.xlistview.XListView;
import com.iwind.Constant.ConstantString;
import com.iwind.adapter.DiscussDetailAdapter;
import com.iwind.adapter.MyAnswerAdapter;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 作者：HuGuoJun
 * 2016/6/13 14:39
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_MyAnswer extends EaseBaseActivity {

    @ViewInject(R.id.title_bar)
    EaseTitleBar title_bar;
    @ViewInject(R.id.lv_myanswer)
    XListView lv_myanswer;
    private List<HashMap<String, String>> mList = new ArrayList<HashMap<String, String>>();
    private MyAnswerAdapter mMyAnswerAdapter;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.act_myanswer);
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
        title_bar.setTitle(getResources().getString(R.string.my_answer));
    }

    @Override
    public void InitData() {
        for (int i = 0; i < 20; i++) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put(ConstantString.NAME, "我回答了这个问题" + i);
            hashMap.put(ConstantString.CONTENT, "本报讯（通讯员 吴川宁 记者 王茸）5日下午，南京交警高速六大队民警执勤时，查获9起未随车携带驾驶证驾驶机动车的交通违法行为，并对相关驾驶员予以处罚。溧阳的赵先生利用星期天驾车到南京游玩，行驶到宁杭高速南京收费站时被拦下例行检查。当交警要其出示驾驶证时，赵先生掏不出来，他解释道，自己有驾驶证，放在了平时上下班开的小车上。“今天天气好，我开了另一辆越野车带家人到南京玩，忘记把驾驶证取出来了。”他解释道，不过，他的解释并未获得交警的通融，他因没带驾驶证被罚款50元记1分。没一会儿，市民李先生也被交警查出没带驾驶证，原来他一直把证放在包里，当天出门时没带包。“我又不是无证驾驶，交警在系统里都能查");
            hashMap.put(ConstantString.READ_COUNT, i * 20 + "");
            hashMap.put(ConstantString.TYPE, "地税");
            hashMap.put(ConstantString.IV_URL, "http://");
            mList.add(hashMap);
        }
        mMyAnswerAdapter = new MyAnswerAdapter(this, mList);
        lv_myanswer.setAdapter(mMyAnswerAdapter);
        mMyAnswerAdapter.notifyDataSetChanged();

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
