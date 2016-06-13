package com.iwind.tj_tax;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.iwind.App.MyApplication;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.w3c.dom.Text;

/**
 * 登录页面
 * 作者：HuGuoJun
 * 2016/6/13 16:03
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_Login extends EaseBaseActivity {
    @ViewInject(R.id.et_username)
    EditText et_username;
    @ViewInject(R.id.et_pass)
    EditText et_pass;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.act_login);
        ViewUtils.inject(this);
        InitView();
        InitData();
        setOnClickListener();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Login();
                break;
        }
    }

    /**
     * 登陆方法
     */
    private void Login() {
        String username = et_username.getText().toString();
        String pass = et_pass.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(context, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(context, getResources().getString(R.string.Password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        MyApplication.SetUserNameAndPwd(username, pass);
        startActivity(new Intent(context, MainActivity.class));
        finish();
    }

    @Override
    public void InitView() {

    }

    @Override
    public void InitData() {

    }

    @Override
    public void setOnClickListener() {

    }
}
