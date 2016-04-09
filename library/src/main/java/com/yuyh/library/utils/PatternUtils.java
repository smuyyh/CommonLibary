package com.yuyh.library.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 格式验证
 *
 * @author yuyuhang.
 * @date 16/4/9.
 */
public class PatternUtils {
    /**
     * 是否符合手机号码格式
     *
     * @param phone
     * @return
     */
    public static boolean isMobilePhoneNum(String phone) {
        Pattern pattern = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 是否符合邮箱地址格式
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String strPattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 是否符合身份证号码格式
     *
     * @param idCard
     * @return
     */
    public static boolean isIdCard(String idCard) {
        String strPattern = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(idCard);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 是否符合yyyy-mm-dd的生日格式
     *
     * @param birthday
     * @return
     */
    public static boolean isBirthday(String birthday) {
        String strPattern = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(birthday);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 从字符串中获取日期
     *
     * @param content
     * @return
     */
    public static String getDateFromString(String content) {
        try {
            String strPattern = "DB_PoliceOfficeWork_(\\d{4}-\\d{2}-\\d{2}).db";
            Pattern pattern = Pattern.compile(strPattern);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                return matcher.group(1);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据模式从原文中截取满足条件的一部分字符串
     *
     * @param strPattern
     * @param content
     * @return
     */
    public static String getOneStrFromString(String strPattern, String content) {
        try {
            Pattern pattern = Pattern.compile(strPattern);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                return matcher.group(1);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
}
