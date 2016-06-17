package com.iwind.tj_tax;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.easemob.easeui.ui.EaseBaseActivity;
import com.iwind.App.MyApplication;
import com.iwind.Constant.ConstantString;
import com.iwind.Constant.ConstantUrl;
import com.iwind.Utils.TaxUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.json.JSONException;
import org.json.JSONObject;
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
            case R.id.et_register:
                startActivity(new Intent(context, Act_Register.class));
                break;
        }
    }

    /**
     * 登陆方法
     */
    private void Login() {
        final String username = et_username.getText().toString();
        final String pass = et_pass.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(context, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(context, getResources().getString(R.string.Password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        RequestParams params = new RequestParams();
        params.addBodyParameter(ConstantString.USER_NAME, username);
        HttpUtils httpUtils = new HttpUtils();
        params.addBodyParameter(ConstantString.PASSWORD, pass);
        httpUtils.send(HttpRequest.HttpMethod.POST, ConstantUrl.BASE_URL + ConstantUrl.LOGIN, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.i("main", responseInfo.result);
                try {
                    JSONObject jsonObject = new JSONObject(responseInfo.result);
                    if (TaxUtils.getInstance().resultStateText(responseInfo.result)) {
                        MyApplication.getInstance().setUserNameAndPwd(username, pass);
                        MyApplication.getInstance().setToken(jsonObject.getString(ConstantString.TOKEN));
                        MyApplication.getInstance().setUserId(jsonObject.getString(ConstantString.USER_ID));
                        Toast.makeText(context, getResources().getString(R.string.login_successful), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, MainActivity.class));
                        finish();
                    } else {
                        Log.i("main", "请求失败");
                    }
                } catch (JSONException e) {
                    Toast.makeText(context, getResources().getString(R.string.data_format_error), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(context, getResources().getString(R.string.server_inner_error), Toast.LENGTH_SHORT)
                        .show();
            }
        });


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
