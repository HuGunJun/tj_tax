package com.easemob.easeui.helper;

import android.content.ContentValues;

import com.easemob.easeui.domain.EaseUser;

import java.util.List;
import java.util.Map;

/**
 * 作者：HuGuoJun
 * 2016/5/27 16:49
 * 邮箱：www.guojunkuaile@qq.com
 */
public interface DaoInterFace {

    public boolean addFriend(EaseUser item);

    public boolean deleteFriend(String whereClause, String[] whereArgs);

    public boolean updateFriend(ContentValues values, String whereClause,
                               String[] whereArgs);

    public Map<String, String> viewCache(String selection,
                                         String[] selectionArgs);

    public List<Map<String, String>> listCache(String selection,
                                               String[] selectionArgs);

    public void clearFeedTable();
}
