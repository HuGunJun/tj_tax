package com.easemob.easeui.utils;


import java.io.File;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.widget.Toast;

import com.easemob.easeui.R;

/**
 * 作者：HuGuoJun
 * 2016/6/3 11:09
 * 邮箱：www.guojunkuaile@qq.com
 */
public class FileUtils {

    // 内存卡根目录
    public static String BASE_DIR = Environment.getExternalStorageDirectory()
            + "/";
    // 用户头像根目录
    public static String USER_HEAD_ICON = "user_head_icon/";

    // 用户头像剪切之后目录
    public static String USER_HEAD_ICON_CUT = "user_head_icon_cut/";

    //错误日志保存路径
    public static String ERROR_DIR = "error_log/";

    // 获取头像图片路径
    public static String getImagePath(Context context, boolean CutOrChoose) {// true为裁剪false为选择
        String path = "";
        if (CutOrChoose) {
            path = BASE_DIR + getApplicationName(context) + USER_HEAD_ICON_CUT;
        } else {
            path = BASE_DIR + getApplicationName(context) + USER_HEAD_ICON;
        }
        File dir = new File(path);
        if (dir.exists() == false) {
            dir.mkdirs();
        }
        return path;
    }


    public static String getErrorLogPath(Context mContext) {
        String path = "";
        path = BASE_DIR + getApplicationName(mContext) + ERROR_DIR;
        File dir = new File(path);
        if (dir.exists() == false) {
            dir.mkdirs();
        }
        return path;
    }

    // 检测内存卡是否存在
    public static boolean CheckExtetnalCard(Context context) {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(context,
                    context.getResources().getString(R.string.scard_off),
                    Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    // 删除目录和文件（使用递归）
    public static void deleteDir(String path) {
        File dir = new File(path);
        if (dir == null || !dir.exists() || !dir.isDirectory()){
            return;
        }
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete();
            else if (file.isDirectory())
                deleteDir(path);
        }
        dir.delete();
    }

    /**
     * 获取应用程序名称
     *
     * @return
     */
    public static String getApplicationName(Context mContext) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = mContext.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName =
                (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName + "/";
    }

}
