package com.xyc.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * 对资源获取的工具类
 */
public class ResourceUtil {

    /**
     * 获得资源文件：字符串
     */
    public static String getStrFromRes(Context context, int resId){
        return context.getResources().getString(resId);
    }

    /**
     *  获得资源文件中的距离  db值
     */
    public static int getDisFromRes(Context context,int resId){
        return context.getResources().getDimensionPixelOffset(resId);
    }

    /**
     * 获取颜色
     * @param resId  颜色的资源id
     */
    public static int getColFromRes(Context context,int resId){
        return context.getResources().getColor(resId);
    }

    /**
     * 获取图片
     * @param resId  图片的资源id
     */
    public static Drawable getDraFromRes(Context context, int resId){
        return context.getResources().getDrawable(resId);
    }
}
