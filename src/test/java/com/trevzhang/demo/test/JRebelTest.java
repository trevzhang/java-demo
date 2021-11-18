package com.trevzhang.demo.test;

import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * @author zhangchunguang.zcg
 * @since 2021/11/18 9:58 下午
 */
public class JRebelTest {

    @Test
    public void test1() throws InterruptedException {
        while (true) {
            JRebelBean.printSomething();
            TimeUnit.SECONDS.sleep(3L);
        }
    }
}
