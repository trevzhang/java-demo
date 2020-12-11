package com.trevzhang.demo.test;

import cn.hutool.core.util.NumberUtil;
import org.junit.Test;

/**
 * 测试数字工具类
 * @author zhangchunguang
 */
public class NumberUtilTest {

    @Test
    public void isNumberTest() {
        String str = "02";
        System.out.println(NumberUtil.isNumber(str));
    }

}
