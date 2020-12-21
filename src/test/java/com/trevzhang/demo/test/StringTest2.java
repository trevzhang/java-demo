package com.trevzhang.demo.test;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 * 验证java8后 字符串常量池在堆中
 * <p>
 * {@code -XX:MaxMetaspaceSize=10M} 可以限定元空间为10M {@code -Xmx10m -Xms10m  -XX:-UseGCOverheadLimit}
 * 可以限定堆大小为10M
 *
 * @author Trevor Zhang
 * @link https://blog.csdn.net/weixin_38106322/article/details/108741864
 * @since 2020/12/10
 */
public class StringTest2 {

    @Test
    public void testGC(String[] args) {
        //保持对象引用，防止被GC
        Set<String> set = new HashSet<String>();
        long i = 0;
        while (true) {
            System.out.println(i);
            //使用intern()方法是因为该方法可以实现运行时新增常量入字符串常量池，这样常量池占用的空间就会不断变大，直至内存溢出
            set.add(String.valueOf(i++).intern());
        }
    }
}
