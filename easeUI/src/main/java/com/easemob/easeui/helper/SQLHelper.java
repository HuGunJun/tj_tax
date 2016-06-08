package com.easemob.easeui.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.easemob.easeui.controller.EaseUI;

import java.util.HashMap;
import java.util.List;

/**
 * 作者：HuGuoJun
 * 2016/5/27 15:14
 * 邮箱：www.guojunkuaile@qq.com
 */
public class SQLHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "database.db";// 数据库名称
    public static final int VERSION = 1;

    public static final String TABLE_USER = "users_list";// 联系人列表

    public static final String EID = "eid";//环信ID
    public static final String NAME = "name";//用户名
    public static final String NICK = "nick";//昵称
    public static final String SEX = "sex";//性别
    public static final String AGE = "age";//年龄
    public static final String AVATAR = "avatar";//头像
    public static final String INITIALETTER = "initialLetter";//首字母
    public static final String ISBLIACK = "isblack";//是否为黑名单或者陌生人


    public static final String TABLE_INVITE = "new_friends_msgs";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_FROM = "username";
    public static final String COLUMN_NAME_GROUP_ID = "groupid";
    public static final String COLUMN_NAME_GROUP_Name = "groupname";
    public static final String COLUMN_NAME_TIME = "time";
    public static final String COLUMN_NAME_REASON = "reason";
    public static final String COLUMN_NAME_STATUS = "status";
    public static final String COLUMN_NAME_ISINVITEFROMME = "isInviteFromMe";
    public static final String COLUMN_NAME_UNREAD_MSG_COUNT = "unreadMsgCount";


    private Context context;

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 联系人
        String sql_contract_list = "create table if not exists " + TABLE_USER
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EID + " TEXT , "
                + NAME + " TEXT , "
                + SEX + " TEXT , "
                + AGE + " TEXT , "
                + NICK + " TEXT , "
                + AVATAR + " TEXT , "
                + INITIALETTER + " TEXT , "
                + ISBLIACK + " TEXT)";
        db.execSQL(sql_contract_list);


        String INIVTE_MESSAGE_TABLE_CREATE = "create table if not exists  "
                + TABLE_INVITE + " ("
                + COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME_FROM + " TEXT, "
                + COLUMN_NAME_GROUP_ID + " TEXT, "
                + COLUMN_NAME_GROUP_Name + " TEXT, "
                + COLUMN_NAME_REASON + " TEXT, "
                + COLUMN_NAME_STATUS + " INTEGER, "
                + COLUMN_NAME_ISINVITEFROMME + " INTEGER, "
                + COLUMN_NAME_UNREAD_MSG_COUNT + " INTEGER, "
                + COLUMN_NAME_TIME + " TEXT); ";

        db.execSQL(INIVTE_MESSAGE_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
