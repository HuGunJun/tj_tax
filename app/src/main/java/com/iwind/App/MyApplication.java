package com.iwind.App;

import android.app.Application;
import android.content.SharedPreferences;

import com.iwind.Constant.ConstantString;

/**
 * 应用程序入口
 * 作者：HuGuoJun
 * 2016/6/13 16:58
 * 邮箱：www.guojunkuaile@qq.com
 */
public class MyApplication extends Application {

    private static MyApplication instance;
    private static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        preferences = getSharedPreferences(ConstantString.USER_INFO, MODE_PRIVATE);
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 获取用户
     *
     * @return
     */
    public String getUserName() {
        return preferences.getString(ConstantString.USER_NAME, "");
    }

    /**
     * @param username
     * @param Pwd
     */
    public void setUserNameAndPwd(String username, String Pwd) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(ConstantString.USER_NAME, username);
        edit.putString(ConstantString.PASSWORD, Pwd);
        edit.commit();
    }


    /**
     * 获取用户id
     *
     * @return
     */
    public String getUserid() {
        return preferences.getString(ConstantString.USER_ID, "");
    }

    /**
     * 保存用户id
     *
     * @param userIdString
     */
    public void setUserId(String userIdString) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(ConstantString.USER_ID, userIdString);
        edit.commit();
    }

    /**
     * 获取Token
     *
     * @return
     */
    public String getToken() {
        return preferences.getString(ConstantString.TOKEN, "");
    }

    /**
     * 保存用户Token
     *
     * @param tokenString
     */
    public void setToken(String tokenString) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(ConstantString.TOKEN, tokenString);
        edit.commit();
    }


    /**
     * 清除用户信息
     */
    public void clearUserInfo() {
        SharedPreferences.Editor edit = preferences.edit();
        edit.clear();
        edit.commit();
    }

}
