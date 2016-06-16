package com.iwind.tj_tax.Question;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.easemob.easeui.utils.EaseAnimationUtils;
import com.easemob.easeui.widget.dropdownmenu.DropDownMenu;
import com.easemob.easeui.widget.xlistview.XListView;
import com.iwind.Constant.ConstantString;
import com.iwind.adapter.GirdDropDownAdapter;
import com.iwind.adapter.QuestionAdapter;
import com.iwind.tj_tax.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 作者：HuGuoJun
 * 2016/6/3 12:31
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_Question extends AppCompatActivity {
    @ViewInject(R.id.lv_news)
    XListView lv_news;
    @ViewInject(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {"行业", "税种"};
    private GirdDropDownAdapter typeadapter, industryadapter;
    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String industrys[] = {"不限", "one", "two", "three", "four"};
    private List<View> popupViews = new ArrayList<>();
    private ListView type_listview, industry_listview;
    private List<HashMap<String, String>> mList = new ArrayList<HashMap<String, String>>();
    private QuestionAdapter mNewsAdapter;
    private float mFirstY;
    private float mCurrentY;
    @ViewInject(R.id.menu)
    LinearLayout menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__news);
        ViewUtils.inject(this);
        InitView();
        InitData();
        SetOnClickListener();

    }

    private void SetOnClickListener() {
        type_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeadapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                mDropDownMenu.closeMenu();
            }
        });
        industry_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                industryadapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : industrys[position]);
                mDropDownMenu.closeMenu();
            }
        });
        lv_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Act_Question_Detail.class);
                intent.putExtra(ConstantString.QUESTION_TITLE, "这是什么特么的标题擦擦擦啊擦擦啊擦");
                startActivity(intent);
            }
        });
        lv_news.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mFirstY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurrentY = event.getY();
                        if (mCurrentY - mFirstY > 0) {
                            // 下滑 显示titleBar
//                            EaseAnimationUtils.showHideTitleBar(true, menu, lv_news);
                        } else if (mFirstY - mCurrentY > 0) {
                            // 上滑 隐藏titleBar
//                            EaseAnimationUtils.showHideTitleBar(false, menu, lv_news);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });

    }


    public void InitView() {
        type_listview = new ListView(this);
        typeadapter = new GirdDropDownAdapter(this, Arrays.asList(citys));
        type_listview.setDividerHeight(0);
        type_listview.setAdapter(typeadapter);

        industry_listview = new ListView(this);
        industryadapter = new GirdDropDownAdapter(this, Arrays.asList(industrys));
        industry_listview.setDividerHeight(0);
        industry_listview.setAdapter(industryadapter);

        popupViews.add(type_listview);
        popupViews.add(industry_listview);

        TextView contentView = new TextView(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentView.setText("内容显示区域");
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 0);
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);

        lv_news.setPullLoadEnable(true);
        lv_news.setPullRefreshEnable(true);


    }

    public void InitData() {
        for (int i = 0; i < 20; i++) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put(ConstantString.CONTENT, "本报讯（通讯员 吴川宁 记者 王茸）5日下午，南京交警高速六大队民警执勤时，查获9起未随车携带驾驶证驾驶机动车的交通违法行为，并对相关驾驶员予以处罚。溧阳的赵先生利用星期天驾车到南京游玩，行驶到宁杭高速南京收费站时被拦下例行检查。当交警要其出示驾驶证时，赵先生掏不出来，他解释道，自己有驾驶证，放在了平时上下班开的小车上。“今天天气好，我开了另一辆越野车带家人到南京玩，忘记把驾驶证取出来了。”他解释道，不过，他的解释并未获得交警的通融，他因没带驾驶证被罚款50元记1分。没一会儿，市民李先生也被交警查出没带驾驶证，原来他一直把证放在包里，当天出门时没带包。“我又不是无证驾驶，交警在系统里都能查");
            hashMap.put(ConstantString.READ_COUNT, "100000");
            hashMap.put(ConstantString.TYPE, "纳服2.3");
            mList.add(hashMap);
        }
        mNewsAdapter = new QuestionAdapter(getApplicationContext(), mList);
        lv_news.setAdapter(mNewsAdapter);
        mNewsAdapter.notifyDataSetChanged();
    }

    public void setOnClickListener() {

    }

    public void onClick(View view) {

    }


    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }
}
