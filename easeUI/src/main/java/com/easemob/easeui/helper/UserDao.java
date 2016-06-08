package com.easemob.easeui.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.easemob.easeui.controller.EaseUI;
import com.easemob.easeui.domain.EaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：HuGuoJun
 * 2016/5/27 16:51
 * 邮箱：www.guojunkuaile@qq.com
 */
public class UserDao implements DaoInterFace {

    private SQLHelper helper = null;

    public UserDao(Context context) {
        helper = new SQLHelper(context);
    }

    @Override
    public boolean addFriend(EaseUser item) {
        boolean flag = false;
        SQLiteDatabase database = null;
        long id = -1;
        try {
            database = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(SQLHelper.EID, item.getEid());
            values.put(SQLHelper.NAME, item.getUsername());
            values.put(SQLHelper.NICK, item.getNick());
            values.put(SQLHelper.SEX, item.getSex());
            values.put(SQLHelper.AGE, item.getAge());
            values.put(SQLHelper.AVATAR, item.getAvatar());
            values.put(SQLHelper.INITIALETTER, item.getInitialLetter());
            values.put(SQLHelper.ISBLIACK, item.getIsblack());
            id = database.insert(SQLHelper.TABLE_USER, null, values);
            flag = (id != -1 ? true : false);
        } catch (Exception e) {
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return flag;
    }

    @Override
    public boolean deleteFriend(String whereClause, String[] whereArgs) {
        boolean flag = false;
        SQLiteDatabase database = null;
        int count = 0;
        try {
            database = helper.getWritableDatabase();
            count = database.delete(SQLHelper.TABLE_USER, whereClause,
                    whereArgs);
            flag = (count > 0 ? true : false);
        } catch (Exception e) {
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return flag;
    }

    @Override
    public boolean updateFriend(ContentValues values, String whereClause,
                                String[] whereArgs) {
        boolean flag = false;
        SQLiteDatabase database = null;
        int count = 0;
        try {
            database = helper.getWritableDatabase();
            count = database.update(SQLHelper.TABLE_USER, values, whereClause,
                    whereArgs);
            flag = (count > 0 ? true : false);
        } catch (Exception e) {
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return flag;
    }

    @Override
    public Map<String, String> viewCache(String selection,
                                         String[] selectionArgs) {
        SQLiteDatabase database = null;
        Cursor cursor = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            database = helper.getReadableDatabase();
            cursor = database.query(true, SQLHelper.TABLE_USER, null,
                    selection, selectionArgs, null, null, null, null);
            int cols_len = cursor.getColumnCount();
            while (cursor.moveToNext()) {
                for (int i = 0; i < cols_len; i++) {
                    String cols_name = cursor.getColumnName(i);
                    String cols_values = cursor.getString(cursor
                            .getColumnIndex(cols_name));
                    if (cols_values == null) {
                        cols_values = "";
                    }
                    map.put(cols_name, cols_values);
                }
            }
        } catch (Exception e) {
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return map;
    }

    @Override
    public List<Map<String, String>> listCache(String selection,
                                               String[] selectionArgs) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        SQLiteDatabase database = null;
        Cursor cursor = null;
        try {
            database = helper.getReadableDatabase();
            cursor = database.query(false, SQLHelper.TABLE_USER, null,
                    selection, selectionArgs, null, null, null, null);
            int cols_len = cursor.getColumnCount();
            while (cursor.moveToNext()) {
                Map<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < cols_len; i++) {

                    String cols_name = cursor.getColumnName(i);
                    String cols_values = cursor.getString(cursor
                            .getColumnIndex(cols_name));
                    if (cols_values == null) {
                        cols_values = "";
                    }
                    map.put(cols_name, cols_values);
                }
                list.add(map);
            }

        } catch (Exception e) {
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return list;
    }

    @Override
    public void clearFeedTable() {
        String sql = "DELETE FROM " + SQLHelper.TABLE_USER + ";";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql);
        String sqls = "update sqlite_sequence set seq=0 where name='"
                + SQLHelper.TABLE_USER + "'";
        db.execSQL(sqls);
    }


}
