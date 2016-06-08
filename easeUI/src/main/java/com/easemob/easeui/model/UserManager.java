package com.easemob.easeui.model;

import android.database.SQLException;


import com.easemob.easeui.controller.EaseUI;
import com.easemob.easeui.domain.EaseUser;
import com.easemob.easeui.helper.SQLHelper;
import com.easemob.easeui.helper.UserDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    public static UserManager userManager;
    /**
     * 默认的用户好友列表
     */
    public static List<EaseUser> User_Contract;
    /**
     * 默认的黑名单列表
     */
    public static List<EaseUser> User_BlackList;
    private UserDao userDao;
    /**
     * 判断数据库中是否存在用户数据
     */
    private boolean userExist = false;

    public UserManager(SQLHelper paramDBHelper) throws SQLException {
        if (userDao == null)
            userDao = new UserDao(paramDBHelper.getContext());
        return;
    }

    /**
     * 初始化好友管理类
     *
     * @param paramDBHelper
     * @throws SQLException
     */
    public synchronized static UserManager getManage(SQLHelper dbHelper) throws SQLException {
        InitData();
        if (userManager == null)
            userManager = new UserManager(dbHelper);
        return userManager;
    }

    private synchronized static void InitData() {
        User_Contract = new ArrayList<EaseUser>();
        User_BlackList = new ArrayList<EaseUser>();

        List<HashMap<String, String>> friendlist = EaseUI.getInstance().getFriendlist();
        List<HashMap<String, String>> blacklist = EaseUI.getInstance().getBlacklist();


        for (int i = 0; i < friendlist.size(); i++) {
            EaseUser user = new EaseUser();
            user.setEid(friendlist.get(i).get(SQLHelper.EID));
            user.setSex(friendlist.get(i).get(SQLHelper.SEX));
            user.setInitialLetter(friendlist.get(i).get(SQLHelper.INITIALETTER));
            user.setAvatar(friendlist.get(i).get(SQLHelper.AVATAR));
            user.setNick(friendlist.get(i).get(SQLHelper.NICK));
            user.setUsername(friendlist.get(i).get(SQLHelper.NAME));
            user.setIsblack("1");
            User_Contract.add(user);
        }

        for (int i = 0; i < blacklist.size(); i++) {
            EaseUser user = new EaseUser();
            user.setEid(friendlist.get(i).get(SQLHelper.EID));
            user.setSex(friendlist.get(i).get(SQLHelper.SEX));
            user.setInitialLetter(friendlist.get(i).get(SQLHelper.INITIALETTER));
            user.setAvatar(friendlist.get(i).get(SQLHelper.AVATAR));
            user.setNick(friendlist.get(i).get(SQLHelper.NICK));
            user.setUsername(friendlist.get(i).get(SQLHelper.NAME));
            user.setIsblack("0");
            User_BlackList.add(user);
        }
    }

    /**
     * 清除所有朋友
     */
    public synchronized void deleteAllFriend() {
        userDao.clearFeedTable();
    }

    /**
     * 获取好友列表
     *
     * @return 数据库存在用户配置 ? 数据库内的用户选择频道 : 默认用户选择频道 ;
     */
    public synchronized List<EaseUser> getUser_Contract() {
        Object cacheList = userDao.listCache(SQLHelper.ISBLIACK + "= ?",
                new String[]{"1"});
        if (cacheList != null && !((List) cacheList).isEmpty()) {
            userExist = true;
            List<Map<String, String>> maplist = (List) cacheList;
            int count = maplist.size();
            List<EaseUser> list = new ArrayList<EaseUser>();
            for (int i = 0; i < count; i++) {
                EaseUser navigate = new EaseUser();
                navigate.setEid(maplist.get(i).get(SQLHelper.EID));
                navigate.setUsername(maplist.get(i).get(SQLHelper.NAME));
                navigate.setAge(maplist.get(i).get(SQLHelper.AGE));
                navigate.setAvatar(maplist.get(i).get(SQLHelper.AVATAR));
                navigate.setInitialLetter(maplist.get(i).get(SQLHelper.INITIALETTER));
                navigate.setNick(maplist.get(i).get(SQLHelper.NICK));
                navigate.setSex(maplist.get(i).get(SQLHelper.SEX));
                navigate.setIsblack(maplist.get(i).get(
                        SQLHelper.ISBLIACK));
                list.add(navigate);
            }
            return list;
        }
        initChannel();
        return User_Contract;
    }

    /**
     * 获取黑名单
     *
     * @return 数据库存在用户配置 ? 数据库内的其它频道 : 默认其它频道 ;
     */
    public synchronized List<EaseUser> getUser_BlackList() {
        Object cacheList = userDao.listCache(SQLHelper.ISBLIACK + "= ?",
                new String[]{"0"});
        List<EaseUser> list = new ArrayList<EaseUser>();
        if (cacheList != null && !((List) cacheList).isEmpty()) {
            List<Map<String, String>> maplist = (List) cacheList;
            int count = maplist.size();
            for (int i = 0; i < count; i++) {
                EaseUser navigate = new EaseUser();
                navigate.setEid(maplist.get(i).get(SQLHelper.EID));
                navigate.setUsername(maplist.get(i).get(SQLHelper.NAME));
                navigate.setAge(maplist.get(i).get(SQLHelper.AGE));
                navigate.setAvatar(maplist.get(i).get(SQLHelper.AVATAR));
                navigate.setInitialLetter(maplist.get(i).get(SQLHelper.INITIALETTER));
                navigate.setNick(maplist.get(i).get(SQLHelper.NICK));
                navigate.setSex(maplist.get(i).get(SQLHelper.SEX));
                navigate.setIsblack(maplist.get(i).get(
                        SQLHelper.ISBLIACK));
                list.add(navigate);
            }
            return list;
        }
        if (userExist) {
            return list;
        }
        cacheList = User_BlackList;
        return (List<EaseUser>) cacheList;
    }

    /**
     * 初始化数据库内的频道数据
     */
    private synchronized void initChannel() {
        deleteAllFriend();
        saveFriendList(User_Contract);
        saveBlackList(User_BlackList);
    }

    /**
     * 保存好友到数据库
     *
     * @param userList
     */
    public synchronized void saveFriendList(List<EaseUser> userList) {
        for (int i = 0; i < userList.size(); i++) {
            EaseUser channelItem = (EaseUser) userList.get(i);
            channelItem.setIsblack("1");
            userDao.addFriend(channelItem);
        }
    }

    /**
     * 保存黑名单到数据库
     *
     * @param otherList
     */
    public synchronized void saveBlackList(List<EaseUser> otherList) {
        for (int i = 0; i < otherList.size(); i++) {
            EaseUser channelItem = (EaseUser) otherList.get(i);
            channelItem.setIsblack("0");
            userDao.addFriend(channelItem);
        }
    }
}
