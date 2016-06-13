package com.iwind.tj_tax;

import android.annotation.TargetApi;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

import com.easemob.easeui.widget.EaseTitleBar;
import com.iwind.tj_tax.Find.Act_Find;
import com.iwind.tj_tax.HomePage.Act_Add_Discuss;
import com.iwind.tj_tax.HomePage.Act_HomePage;
import com.iwind.tj_tax.Mine.Act_Message;
import com.iwind.tj_tax.Mine.Act_Mine;
import com.iwind.tj_tax.Question.Act_Question;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * 作者：HuGuoJun
 * 2016/6/2 11:20
 * 邮箱：www.guojunkuaile@qq.com
 */
public class MainActivity extends TabActivity {

    private TabHost tabHost;
    private RadioGroup radioderGroup;
    @ViewInject(R.id.title_bar)
    EaseTitleBar title_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.titile_color);//通知栏所需颜色
        }
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        InitView();
        InitData();
        SetOnClickListener();
    }

    /**
     * 设置点击事件
     */
    private void SetOnClickListener() {
        title_bar.setRightImageClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Act_Add_Discuss.class));
            }
        });
        title_bar.setRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Act_Message.class));
            }
        });
    }

    /**
     * 设置sdk版本属性 在主界面保持标题栏一致
     *
     * @param on
     */
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void InitView() {
        tabHost = getTabHost();
        radioderGroup = (RadioGroup) findViewById(R.id.main_radio);
        title_bar.setTitle(getResources().getString(R.string.bottom_menu_home));
        title_bar.setBackgroundColor(getResources().getColor(R.color.titile_color));
        title_bar.setRightImageResource(R.mipmap.ic_launcher);
        title_bar.setRightText(getResources().getString(R.string.message));
        title_bar.setRightImageAndTextVisiable(true, false);
    }

    public void InitData() {
        tabHost.addTab(tabHost.newTabSpec("1").setIndicator("1").setContent(new Intent(this, Act_HomePage.class)));
        tabHost.addTab(tabHost.newTabSpec("2").setIndicator("2").setContent(new Intent(this, Act_Question.class)));
        tabHost.addTab(tabHost.newTabSpec("3").setIndicator("3").setContent(new Intent(this, Act_Find.class)));
        tabHost.addTab(tabHost.newTabSpec("4").setIndicator("4").setContent(new Intent(this, Act_Mine.class)));
        radioderGroup = (RadioGroup) findViewById(R.id.main_radio);
        radioderGroup.check(R.id.mainTabs_radio_home);//默认第一个按钮
        radioderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.mainTabs_radio_home:
                        tabHost.setCurrentTabByTag("1");
                        title_bar.setTitle(getResources().getString(R.string.bottom_menu_home));
                        title_bar.setRightLayoutVisibility(View.VISIBLE);
                        title_bar.setRightImageAndTextVisiable(true, false);
                        break;
                    case R.id.mainTabs_radio_news:
                        tabHost.setCurrentTabByTag("2");
                        title_bar.setTitle(getResources().getString(R.string.bottom_menu_question));
                        title_bar.setRightLayoutVisibility(View.INVISIBLE);
                        break;
                    case R.id.mainTabs_radio_find:
                        tabHost.setCurrentTabByTag("3");
                        title_bar.setTitle(getResources().getString(R.string.bottom_menu_find));
                        title_bar.setRightLayoutVisibility(View.INVISIBLE);
                        break;
                    case R.id.mainTabs_radio_mine:
                        tabHost.setCurrentTabByTag("4");
                        title_bar.setTitle(getResources().getString(R.string.bottom_menu_mine));
                        title_bar.setRightLayoutVisibility(View.VISIBLE);
                        title_bar.setRightImageAndTextVisiable(false, true);
                        break;
                }
            }
        });

    }
}
