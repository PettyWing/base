package com.komect.utils;

import android.text.TextUtils;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 为了单测，使用此类代替android原生的TextUtils类
 * Author by hf
 * Create on 16/11/23
 */
public class StringUtil {
    /**
     * 判断两个字符串是否一致
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) {
            return true;
        }
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(String... group) {
        return group == null || group.length == 0;
    }

    /**
     * 检查目标字符串是否在指定的字符串数组里有相同的值
     *
     * @param a     目标文本
     * @param group 字符串数组
     */
    public static boolean inGroup(CharSequence a, String... group) {
        if (!TextUtils.isEmpty(a) && !isEmpty(group)) {
            for (String string : group) {
                if (TextUtils.equals(a, string)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 验证字符串是否包含一个电话号码（手机或座机）
     */
    public static boolean isMobile(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        }
        boolean result = phone.matches("1\\d{10}");
        boolean lengthMatch = phone.length() >= 7 && phone.length() <= 11;
        return lengthMatch && result;
    }


    /**
     * 检查密码强度有效性，规则：八位、小写字母、大写字母、数字、符号
     */
    public static boolean checkPwdSecurity(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            return false;
        }
        return pwd.matches("^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{6,16}$");
    }


    public static boolean isRightPhone(String phoneNum) {
        if (TextUtils.isEmpty(phoneNum)) {
            return false;
        }
        String phoneReg = "1\\d{10}";
        return phoneNum.matches(phoneReg);
    }


    /**
     * URL编码
     */
    public static String toURLEncoded(String paramString) {
        if (paramString == null || "".equals(paramString)) {
            return "";
        }

        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * 含数字和字母的密码验证 不小于6位数，不大于18位数，不包含空格
     */
    public static boolean pswStringNum(String psw) {
        boolean flag = false;

        try {
            // 限定特殊字符
            //(?!^\\d+$)不能全是数字
            //(?!^[a-z]+$)不能全是小写字母
            //(?!^[A-Z]+$)不能全是大写字母
            //((?!^[_#@]+$)不能全是符号
            // String reg = "^(?![\\d]+$)(?![a-zA-Z]+$)(?![!#$%^&*]+$)[\\da-zA-Z!#$%^&*]{6,20}$";
            String reg = "^[^\\s]{6,18}$";
            //            String reg = "(?!^[0-9]+$)(?!^[a-z]+$)(?!^[A-Z]+$)(?!^[~!@#$%^&*()_+`\\-={}\n" + ":\";'<>?,
            // .\\/]+$).{8,18}";
            Pattern p = Pattern.compile(reg);
            Matcher m = p.matcher(psw);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }

        return flag;
    }


    /**
     * 验证是否为正确的邮箱地址
     *
     * @param email 待验证邮箱地址
     * @return 正确返回true，否则false
     */
    public static boolean isRightEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        String emailFilter = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return email.matches(emailFilter);
    }


    /**
     * 验证是否为正确的密码 规则为6到16位的字母和数字组合
     *
     * @param pwd 待验证密码
     * @return 正确返回true，否则false
     */
    public static boolean isRightPwd(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            return false;
        }

        int len = pwd.length();

        return !(len < 6 || len > 16);
    }


    /**
     * 验证是否为正确的临时短信密码 规则为6位数字组合
     *
     * @param smsPwd 待验证密码
     * @return 正确返回true，否则false
     */
    public static boolean isRightTempSmsPwd(String smsPwd) {
        if (TextUtils.isEmpty(smsPwd)) {
            return false;
        }
        String lengthReg = "\\d{6}";// 6位数字组合
        if (!smsPwd.matches(lengthReg)) {
            return false;
        }

        // Pattern letterReg = Pattern.compile("[a-zA-Z]+");// 必须含有字母
        // Pattern figureReg = Pattern.compile("[0-9]+");// 必须含有数字
        // Matcher mathcer = letterReg.matcher(pwd);
        // if (!mathcer.find()) {
        // return false;
        // }
        // mathcer.reset().usePattern(figureReg);
        // if (!mathcer.find()) {
        // return false;
        // }

        return true;
    }


    /**
     * 身份证正则表达式
     */
    private static final String IDCARD =
            "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +
                    "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +
                    "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";


    public static boolean isIdCard(String string) {
        if (TextUtils.isEmpty(string)) {
            return false;
            //        } else if (string.trim().length() == 15 || string.trim().length() == 18) {
            //            return string.matches(IDCARD);
            //        } else {
            //            return false;
        } else {
            return string.trim().length() == 15 || string.trim().length() == 18;
        }
    }

    /**
     * 根据用户名的不同长度，来进行替换 ，达到保密效果
     *
     * @param userName 用户名
     * @return 替换后的用户名
     */
    public static String userNameReplaceWithStar(String userName) {

        if (TextUtils.isEmpty(userName)) {
            return "";
        }

        StringBuffer userNameAfterReplaced = new StringBuffer(userName.substring(0, 1));

        for (int i = 0; i < userName.length() - 1; i++) {
            userNameAfterReplaced.append("*");
        }

        return userNameAfterReplaced.toString();
    }

    public static String phoneReplaceWithStar(String phoneNum, boolean half) {
        if (TextUtils.isEmpty(phoneNum)) {
            return "";
        }
        if (half) {
            return replaceAction(phoneNum, "(?<=\\d{3})\\d(?=\\d{4})");
        } else {
            return replaceAction(phoneNum, "(?<=\\d{3})\\d(?=\\d{0})");
        }

    }

    /**
     * 实际替换动作
     *
     * @param username username
     * @param regular  正则
     * @return
     */
    private static String replaceAction(String username, String regular) {
        return username.replaceAll(regular, "*");
    }

    /**
     * 身份证号替换，保留前四位和后四位
     * <p>
     * 如果身份证号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param idCard 身份证号
     * @return
     */
    public static String idCardReplaceWithStar(String idCard) {

        if (idCard == null || idCard.isEmpty()) {
            return null;
        } else {
            return replaceAction(idCard, "(?<=\\d{4})\\d(?=\\d{4})");
        }
    }

    /**
     * 银行卡替换，保留后四位
     * <p>
     * 如果银行卡号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param bankCard 银行卡号
     * @return
     */
    public static String bankCardReplaceWithStar(String bankCard) {

        if (bankCard == null || bankCard.isEmpty()) {
            return null;
        } else {
            return replaceAction(bankCard, "(?<=\\d{0})\\d(?=\\d{4})");
        }
    }

    public static void copy(List src, List dest) {
        for (int i = 0; i < src.size(); i++) {
            Object obj = src.get(i);
            if (obj instanceof List) {
                dest.add(new ArrayList());
                copy((List) obj, (List) ((List) dest).get(i));
            } else {
                dest.add(obj);
            }
        }
    }

    public static Date string2Date(int year, int month, int day) {
        String dateStr = year + "-" + month + "-" + day;         //注意format的格式要与日期String的格式相匹配
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
