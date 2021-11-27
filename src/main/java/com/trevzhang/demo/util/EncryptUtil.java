package com.trevzhang.demo.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangchunguang.zcg
 * @since 2021/11/23 8:52 下午
 */
public class EncryptUtil {
    /**
     * 邮箱的打码隐藏加星号* 参数异常直接返回null
     *
     * @param str   需要打码的邮箱
     * @param asteriskCount 需要打码几位，即星号的数量
     * @return 处理完成的打码邮箱字符串
     */
    public static String mosaic(String str, int asteriskCount) {
        //不能为空
        if (StringUtils.isBlank(str)) {
            return null;
        }
        //需要打码的长度不能大于字符串长度
        if (asteriskCount > str.length()) {
            return null;
        }
        //需要打码的不能小于0
        if (asteriskCount < 0 ) {
            return null;
        }
        //计算*的数量
        StringBuilder asteriskStr = new StringBuilder();
        for (int i = 0; i < asteriskCount; i++) {
            asteriskStr.append("*");
        }
        asteriskStr.append("@");
        //打码
        String regex = "(\\w)([-\\w.+]*@)(([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14})";
        return str.replaceAll(regex, "$1" + asteriskStr + "$3");
    }

    public static void main(String[] args) {
        String email = "chin627@163.com";
        System.out.println(mosaic(email,3));

    }
}
