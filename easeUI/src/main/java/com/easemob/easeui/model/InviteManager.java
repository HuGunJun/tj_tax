package com.easemob.easeui.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.easemob.easeui.domain.InviteMessage;
import com.easemob.easeui.helper.InviteMessgeDao;
import com.easemob.easeui.helper.SQLHelper;
import com.easemob.easeui.helper.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * User: HuGuoJun
 * Date: 2016-05-28
 * Time: 18:47
 * Email: www.guojunkuaile@qq.com
 * QQ: 1397883456
 */
public class InviteManager {

    public static InviteManager inviteManager;
    InviteMessgeDao dao;

    public InviteManager(SQLHelper paramDBHelper) throws SQLException {
        if (dao == null)
            dao = new InviteMessgeDao(paramDBHelper.getContext());
        return;
    }

    /**
     * 初始化消息管理类
     *
     * @param paramDBHelper
     * @throws SQLException
     */
    public synchronized static InviteManager getManage(SQLHelper dbHelper) throws SQLException {
        if (inviteManager == null)
            inviteManager = new InviteManager(dbHelper);
        return inviteManager;
    }

    /**
     * 保存message
     *
     * @param message
     * @return 返回这条messaged在db中的id
     */
    public synchronized Integer saveMessage(InviteMessage message) {
        return dao.saveMessage(message);
    }

    /**
     * 更新message
     *
     * @param msgId
     * @param values
     */
    public synchronized void updateMessage(int msgId, ContentValues values) {
        dao.updateMessage(msgId, values);
    }

    /**
     * 获取messges
     *
     * @return
     */
    public synchronized List<InviteMessage> getMessagesList() {
        return dao.getMessagesList();
    }

    public synchronized void deleteMessage(String from) {
        dao.deleteMessage(from);
    }

    public synchronized int getUnreadMessagesCount() {
        return dao.getUnreadNotifyCount();
    }

    public synchronized void saveUnreadMessageCount(int count) {
        dao.setUnreadNotifyCount(count);
    }


}
