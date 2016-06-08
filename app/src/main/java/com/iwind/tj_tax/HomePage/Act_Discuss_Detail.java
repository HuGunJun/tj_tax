package com.iwind.tj_tax.HomePage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.easemob.easeui.widget.EaseTitleBar;
import com.easemob.easeui.widget.xlistview.XListView;
import com.iwind.Constant.ConstantString;
import com.iwind.adapter.DiscussDetailAdapter;
import com.iwind.adapter.HomePageAdapter;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 讨论详情页面
 * 作者：HuGuoJun
 * 2016/6/8 9:35
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_Discuss_Detail extends EaseBaseActivity {
    @ViewInject(R.id.title_bar)
    EaseTitleBar mEaseTitleBar;
    @ViewInject(R.id.lv_question_describe)
    LinearLayout lv_question_describe;
    @ViewInject(R.id.lv_discuss_detail)
    XListView lv_discuss_detail;
    View discuss_detail_header;
    private List<HashMap<String, String>> mList = new ArrayList<HashMap<String, String>>();
    private DiscussDetailAdapter mDiscussDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__discuss__detail);
        ViewUtils.inject(this);
        InitView();
        InitData();
        setOnClickListener();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lv_add_answer:
                startActivity(new Intent(context, Act_Add_Answer.class));
                break;
            case R.id.lv_collection_talk:
                break;
        }
    }

    @Override
    public void InitView() {
        mEaseTitleBar.setTitle(getIntent().getExtras().getString(ConstantString.DISSCUSS_TITLE));
        mEaseTitleBar.setLeftImageResource(R.drawable.ease_mm_title_back);
        mEaseTitleBar.setRightImageResource(R.drawable.ic_launcher);
        lv_discuss_detail.setPullRefreshEnable(false);
        lv_discuss_detail.setPullLoadEnable(false);
        discuss_detail_header = LayoutInflater.from(context).inflate(R.layout.view_discuss_detail_header, lv_discuss_detail, false);
    }

    @Override
    public void InitData() {

        for (int i = 0; i < 20; i++) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put(ConstantString.NAME, "我商家哦张" + i);
            hashMap.put(ConstantString.CONTENT, "本报讯（通讯员 吴川宁 记者 王茸）5日下午，南京交警高速六大队民警执勤时，查获9起未随车携带驾驶证驾驶机动车的交通违法行为，并对相关驾驶员予以处罚。溧阳的赵先生利用星期天驾车到南京游玩，行驶到宁杭高速南京收费站时被拦下例行检查。当交警要其出示驾驶证时，赵先生掏不出来，他解释道，自己有驾驶证，放在了平时上下班开的小车上。“今天天气好，我开了另一辆越野车带家人到南京玩，忘记把驾驶证取出来了。”他解释道，不过，他的解释并未获得交警的通融，他因没带驾驶证被罚款50元记1分。没一会儿，市民李先生也被交警查出没带驾驶证，原来他一直把证放在包里，当天出门时没带包。“我又不是无证驾驶，交警在系统里都能查");
            hashMap.put(ConstantString.READ_COUNT, i * 20 + "");
            hashMap.put(ConstantString.TYPE, "地税");
            hashMap.put(ConstantString.IV_URL, "http://");
            mList.add(hashMap);
        }
        mDiscussDetailAdapter = new DiscussDetailAdapter(this, mList);
        lv_discuss_detail.removeHeaderView(discuss_detail_header);
        lv_discuss_detail.addHeaderView(discuss_detail_header);
        lv_discuss_detail.setAdapter(mDiscussDetailAdapter);
        mDiscussDetailAdapter.notifyDataSetChanged();

    }

    @Override
    public void setOnClickListener() {
        mEaseTitleBar.setRightLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "分享", Toast.LENGTH_SHORT).show();
            }
        });
        mEaseTitleBar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mEaseTitleBar.setCenterLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lv_question_describe.getVisibility() == View.VISIBLE) {
                    lv_question_describe.setVisibility(View.GONE);
                } else {
                    lv_question_describe.setVisibility(View.VISIBLE);
                }
            }
        });
        lv_discuss_detail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 1) {
                    Intent intent = new Intent(context, Act_Answer_Detail.class);
                    intent.putExtra(ConstantString.DISSCUSS_TITLE, "这是什么特么的标题擦擦擦啊擦擦啊擦");
                    startActivity(intent);
                }
            }
        });
    }
}