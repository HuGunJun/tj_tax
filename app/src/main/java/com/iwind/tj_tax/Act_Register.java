package com.iwind.tj_tax;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.iwind.App.MyApplication;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 注册页面
 * 作者：HuGuoJun
 * 2016/6/13 17:30
 * 邮箱：www.guojunkuaile@qq.com
 */
public class Act_Register extends EaseBaseActivity {

    @ViewInject(R.id.et_username)
    EditText et_username;
    @ViewInject(R.id.et_vificode)
    EditText et_vificode;
    @ViewInject(R.id.et_pass)
    EditText et_pass;
    @ViewInject(R.id.cb_introduce)
    CheckBox cb_introduce;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.act_register);
        ViewUtils.inject(this);
        InitView();
        InitData();
        setOnClickListener();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                Register();
                break;
            case R.id.btn_get_vifi_code:
                break;
        }
    }

    /**
     * 注册方法
     */
    private void Register() {


        String username = et_username.getText().toString();
        String pass = et_pass.getText().toString();
        String vifi_code = et_vificode.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(context, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(vifi_code)) {
            Toast.makeText(context, getResources().getString(R.string.vifi_code_can_not_be_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(context, getResources().getString(R.string.Password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (!cb_introduce.isChecked()) {
            Toast.makeText(context, getResources().getString(R.string.please_read_introduce), Toast.LENGTH_SHORT).show();
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
