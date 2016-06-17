package com.iwind.tj_tax;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    @ViewInject(R.id.btn_get_vifi_code)
    Button btn_get_vifi_code;

    private TimeCount time;


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
                SendSmsCode();
                break;
        }
    }

    /**
     * 发送短信验证码
     */
    private void SendSmsCode() {
        String username = et_username.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(context, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        RequestParams params = new RequestParams();
        params.addBodyParameter(ConstantString.PHONE_NUM, username);
        params.addBodyParameter("type", "1");
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.POST, ConstantUrl.BASE_URL + ConstantUrl.SEND_SMSCODE,
                params, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        try {
                            JSONObject jsonObject = new JSONObject(responseInfo.result);
                            if (TaxUtils.getInstance().resultStateText(responseInfo.result)) {
                                Toast.makeText(context, getResources().getString(R.string.sms_code_send_success), Toast.LENGTH_SHORT).show();
                                time.start();// 开始计时
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


    /**
     * 注册方法
     */
    private void Register() {

        final String username = et_username.getText().toString();
        final String pass = et_pass.getText().toString();
        final String vifi_code = et_vificode.getText().toString();
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

        RequestParams params = new RequestParams();
        params.addBodyParameter(ConstantString.PHONE_NUM, username);
        params.addBodyParameter(ConstantString.PASSWORD, pass);
        params.addBodyParameter(ConstantString.SMSCODE, vifi_code);
        params.addBodyParameter(ConstantString.SMSYTPE, "1");

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.POST, ConstantUrl.BASE_URL + ConstantUrl.REGISTER,
                params, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Log.i("main", responseInfo.result);
                        try {
                            JSONObject jsonObject = new JSONObject(responseInfo.result);
                            if (TaxUtils.getInstance().resultStateText(responseInfo.result)) {
                                Toast.makeText(context, getResources().getString(R.string.register_successful), Toast.LENGTH_SHORT).show();
                                MyApplication.getInstance().setUserNameAndPwd(username, pass);
                                MyApplication.getInstance().setUserId(jsonObject.getString(ConstantString.USER_ID));
                                MyApplication.getInstance().setToken(jsonObject.getString(ConstantString.TOKEN));
                                startActivity(new Intent(context, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(context, jsonObject.getString(ConstantString.RESULT_INFO), Toast.LENGTH_SHORT).show();
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
        time = new TimeCount(60000, 1000);
    }

    @Override
    public void InitData() {

    }

    @Override
    public void setOnClickListener() {

    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            btn_get_vifi_code.setText(getResources().getString(R.string.get_sms_code));
            btn_get_vifi_code.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            btn_get_vifi_code.setClickable(false);//防止重复点击
            btn_get_vifi_code.setText(millisUntilFinished / 1000 + "");
        }
    }
}
