package com.easemob.easeui.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.easemob.easeui.domain.InviteMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * User: HuGuoJun
 * Date: 2016-05-28
 * Time: 18:54
 * Email: www.guojunkuaile@qq.com
 * QQ: 1397883456
 */
public class InviteMessgeDao {


    private SQLHelper dbHelper = null;

    public InviteMessgeDao(Context context) {
        dbHelper = new SQLHelper(context);
    }


    /**
     * 保存message
     *
     * @param message
     * @return 返回这条messaged在db中的id
     */
    public synchronized Integer saveMessage(InviteMessage message) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int id = -1;
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(SQLHelper.COLUMN_NAME_FROM, message.getFrom());
            values.put(SQLHelper.COLUMN_NAME_GROUP_ID, message.getGroupId());
            values.put(SQLHelper.COLUMN_NAME_GROUP_Name, message.getGroupName());
            values.put(SQLHelper.COLUMN_NAME_REASON, message.getReason());
            values.put(SQLHelper.COLUMN_NAME_TIME, message.getTime());
            values.put(SQLHelper.COLUMN_NAME_STATUS, message.getStatus().ordinal());
            db.insert(SQLHelper.TABLE_INVITE, null, values);

            Cursor cursor = db.rawQuery("select last_insert_rowid() from " + SQLHelper.TABLE_INVITE, null);
            if (cursor.moveToFirst()) {
                id = cursor.getInt(0);
            }

            cursor.close();
        }
        return id;
    }

    /**
     * 更新message
     *
     * @param msgId
     * @param values
     */
    synchronized public void updateMessage(int msgId, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.update(SQLHelper.TABLE_INVITE, values, SQLHelper.COLUMN_NAME_ID + " = ?", new String[]{String.valueOf(msgId)});
        }
    }

    /**
     * 获取messges
     *
     * @return
     */
    synchronized public List<InviteMessage> getMessagesList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<InviteMessage> msgs = new ArrayList<InviteMessage>();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + SQLHelper.TABLE_INVITE + " desc", null);
            while (cursor.moveToNext()) {
                InviteMessage msg = new InviteMessage();
                int id = cursor.getInt(cursor.getColumnIndex(SQLHelper.COLUMN_NAME_ID));
                String from = cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_NAME_FROM));
                String groupid = cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_NAME_GROUP_ID));
                String groupname = cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_NAME_GROUP_Name));
                String reason = cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_NAME_REASON));
                long time = cursor.getLong(cursor.getColumnIndex(SQLHelper.COLUMN_NAME_TIME));
                int status = cursor.getInt(cursor.getColumnIndex(SQLHelper.COLUMN_NAME_STATUS));

                msg.setId(id);
                msg.setFrom(from);
                msg.setGroupId(groupid);
                msg.setGroupName(groupname);
                msg.setReason(reason);
                msg.setTime(time);
                if (status == InviteMessage.InviteMesageStatus.BEINVITEED.ordinal())
                    msg.setStatus(InviteMessage.InviteMesageStatus.BEINVITEED);
                else if (status == InviteMessage.InviteMesageStatus.BEAGREED.ordinal())
                    msg.setStatus(InviteMessage.InviteMesageStatus.BEAGREED);
                else if (status == InviteMessage.InviteMesageStatus.BEREFUSED.ordinal())
                    msg.setStatus(InviteMessage.InviteMesageStatus.BEREFUSED);
                else if (status == InviteMessage.InviteMesageStatus.AGREED.ordinal())
                    msg.setStatus(InviteMessage.InviteMesageStatus.AGREED);
                else if (status == InviteMessage.InviteMesageStatus.REFUSED.ordinal())
                    msg.setStatus(InviteMessage.InviteMesageStatus.REFUSED);
                else if (status == InviteMessage.InviteMesageStatus.BEAPPLYED.ordinal()) {
                    msg.setStatus(InviteMessage.InviteMesageStatus.BEAPPLYED);
                }
                msgs.add(msg);
            }
            cursor.close();
        }
        return msgs;
    }

    /**
     * 删除要求消息
     *
     * @param from
     */
    synchronized public void deleteMessage(String from) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(SQLHelper.TABLE_INVITE, SQLHelper.COLUMN_NAME_FROM + " = ?", new String[]{from});
        }
    }

    public synchronized int getUnreadNotifyCount() {
        int count = 0;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select " + SQLHelper.COLUMN_NAME_UNREAD_MSG_COUNT + " from " + SQLHelper.TABLE_INVITE, null);
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            cursor.close();
        }
        return count;
    }

    public synchronized void setUnreadNotifyCount(int count) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(SQLHelper.COLUMN_NAME_UNREAD_MSG_COUNT, count);
            db.update(SQLHelper.TABLE_INVITE, values, null, null);
        }
    }
}
