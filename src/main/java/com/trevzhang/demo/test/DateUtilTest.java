package com.trevzhang.demo.test;

import cn.hutool.core.date.DateUtil;
import java.util.Date;

/**
 * @author Trevor Zhang
 * @since 2020/10/9 2:57 下午
 */
public class DateUtilTest {

    public static void main(String[] args) {
        System.out.println(DateUtil.compare(new Date(), DateUtil.yesterday()));
        System.out.println(DateUtil.compare(new Date(), new Date()));
        System.out.println(DateUtil.compare(DateUtil.yesterday(),new Date()));
    }
}
