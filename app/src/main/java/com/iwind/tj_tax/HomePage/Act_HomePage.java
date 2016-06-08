package com.iwind.tj_tax.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.easemob.easeui.widget.xlistview.XListView;
import com.iwind.Constant.ConstantString;
import com.iwind.adapter.HomePageAdapter;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 首页
 * 作者：HuGuoJun
 * 2016/6/2 9:30
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_HomePage extends AppCompatActivity {

    @ViewInject(R.id.lv_homepage)
    XListView lv_homepage;

    private List<HashMap<String, String>> mList = new ArrayList<HashMap<String, String>>();
    private HomePageAdapter mHomePageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__home_page);
        ViewUtils.inject(this);
        InitView();
        InitData();
        SetOnListener();
    }

    /**
     * 设置监听事件
     */
    private void SetOnListener() {
        lv_homepage.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });
        lv_homepage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Act_HomePage.this, Act_Discuss_Detail.class);
                intent.putExtra(ConstantString.DISSCUSS_TITLE, "这是什么特么的标题擦擦擦啊擦擦啊擦");
                startActivity(intent);
            }
        });
    }

    public void InitView() {
        lv_homepage.setPullLoadEnable(true);
        lv_homepage.setPullRefreshEnable(true);
    }

    public void InitData() {
        for (int i = 0; i < 20; i++) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put(ConstantString.NAME, "我商家哦张" + i);
            hashMap.put(ConstantString.CONTENT, "本报讯（通讯员 吴川宁 记者 王茸）5日下午，南京交警高速六大队民警执勤时，查获9起未随车携带驾驶证驾驶机动车的交通违法行为，并对相关驾驶员予以处罚。溧阳的赵先生利用星期天驾车到南京游玩，行驶到宁杭高速南京收费站时被拦下例行检查。当交警要其出示驾驶证时，赵先生掏不出来，他解释道，自己有驾驶证，放在了平时上下班开的小车上。“今天天气好，我开了另一辆越野车带家人到南京玩，忘记把驾驶证取出来了。”他解释道，不过，他的解释并未获得交警的通融，他因没带驾驶证被罚款50元记1分。没一会儿，市民李先生也被交警查出没带驾驶证，原来他一直把证放在包里，当天出门时没带包。“我又不是无证驾驶，交警在系统里都能查");
            hashMap.put(ConstantString.READ_COUNT, "100000");
            hashMap.put(ConstantString.TYPE, "地税");
            hashMap.put(ConstantString.IV_URL, "http://");
            mList.add(hashMap);
        }
        mHomePageAdapter = new HomePageAdapter(this, mList);
        lv_homepage.setAdapter(mHomePageAdapter);
        mHomePageAdapter.notifyDataSetChanged();
    }

    public void onClick(View view) {

    }
}
