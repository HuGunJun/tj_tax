package com.easemob.easeui.widget.loadding;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
/**
 * 作者：HuGuoJun
 * 2016/5/18 10:14
 * 邮箱：www.guojunkuaile@qq.com
 */
public class ParamsCreator {
	private Context context;
    private int screenWidth;//屏幕宽度
    private int screenHeight;//屏幕高度
    private int densityDpi;//像素密度
    public ParamsCreator(Context context){
    	this.context = context;
    	WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    	screenWidth = wm.getDefaultDisplay().getWidth();
    	screenHeight = wm.getDefaultDisplay().getHeight();
    	DisplayMetrics metric = new DisplayMetrics();
    	wm.getDefaultDisplay().getMetrics(metric);
    	densityDpi = metric.densityDpi;
    }
    
	/**
	 * 获得默认圆的半径
	 */
	public int getDefaultCircleRadius(){
    	if(screenWidth >= 1400){//1440
    		return 40;
    	}
    	if(screenWidth >= 1000){//1080
    		if(densityDpi >=480)
        		return 38;
        	if(densityDpi >= 320)
        		return 38;
        	return 38;
    	}
    	if(screenWidth >= 700){//720
        	if(densityDpi >= 320)
        		return 24;
        	if(densityDpi >= 240)
        		return 24;
        	if(densityDpi >= 160)
        		return 24;
        	return 24;
    	}
    	if(screenWidth >= 500){//540
        	if(densityDpi >= 320)
        		return 20;
        	if(densityDpi >= 240)
        		return 20;
        	if(densityDpi >= 160)
        		return 20;
        	return 20;
    	}
    	return 20;
	}
	/**
	 * 获得默认圆的间距
	 */
	public int getDefaultCircleSpacing(){
    	if(screenWidth >= 1400){//1440
    		return 10;
    	}
    	if(screenWidth >= 1000){//1080
    		if(densityDpi >=480)
        		return 10;
        	if(densityDpi >= 320)
        		return 10;
        	return 10;
    	}
    	if(screenWidth >= 700){//720
        	if(densityDpi >= 320)
        		return 6;
        	if(densityDpi >= 240)
        		return 6;
        	if(densityDpi >= 160)
        		return 6;
        	return 6;
    	}
    	if(screenWidth >= 500){//540
        	if(densityDpi >= 320)
        		return 3;
        	if(densityDpi >= 240)
        		return 3;
        	if(densityDpi >= 160)
        		return 3;
        	return 3;
    	}
    	return 3;
	}
}
