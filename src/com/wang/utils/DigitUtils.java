package com.wang.utils;

import java.util.regex.Pattern;

/**
 * 关于数字判断的实用类
 */
public class DigitUtils {
    private static final Pattern BOOK_NUM = Pattern.compile("^[1-9]\\d*$");
    private static final Pattern BOOK_PRICE = Pattern.compile("^((0\\.\\d+)|([1-9]\\d*\\.\\d+)|([1-9]+\\d*))$");

    //图书库存规范
    public static boolean notBookNum(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return ! BOOK_NUM.matcher(str).matches();
    }

    //图书价格规范
    public static boolean notBookPrice(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return ! BOOK_PRICE.matcher(str).matches();
    }
}
