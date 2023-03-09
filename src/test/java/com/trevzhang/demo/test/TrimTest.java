package com.trevzhang.demo.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author zhangchunguang.zcg
 * @since 2023/3/9 16:45
 */
public class TrimTest {

    @Test
    public void testTrim() {
        String testStr = "         泰     国                                                         ";
        String trimed = StringUtils.trim(testStr);
        System.out.println("begin{"+testStr+"}end");
        System.out.println("begin{"+trimed+"}end");
    }
}
