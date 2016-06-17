package com.iwind.Utils;

import com.iwind.Constant.ConstantString;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：HuGuoJun
 * 2016/6/17 17:05
 * 邮箱：www.guojunkuaile@qq.com
 */
public class TaxUtils {

    public static TaxUtils instance = new TaxUtils();


    public static TaxUtils getInstance() {
        if (instance == null)
            return new TaxUtils();
        return instance;
    }


    /**
     * 接口返回状态判断
     *
     * @param stateString
     * @return
     */
    public boolean resultStateText(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        if (jsonObject.getString(ConstantString.RESULT_STATE).equals(ConstantString.STATE)) {
            return true;
        } else {
            return false;
        }
    }
}
