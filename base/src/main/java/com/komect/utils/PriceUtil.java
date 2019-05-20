package com.komect.utils;

import java.text.DecimalFormat;

/**
 * Created by xieyusheng on 2019/4/25.
 */

public class PriceUtil {
    /**
     * 将float转为String
     */
    public static String float2String(float price) {
        if (Math.round(price) == price) {
            return Math.round(price) + "";
        } else {
            DecimalFormat dcFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            return dcFormat.format(price);//format 返回的是字符串
        }
    }
}
