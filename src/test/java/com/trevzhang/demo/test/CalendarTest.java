package com.trevzhang.demo.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @author Murakami Haruki
 * @since 2024/2/2 15:56
 */
public class CalendarTest {

    @Test
    public void testCalendarSecond() throws InterruptedException {
        // 创建一个Calendar对象
        Calendar calendar = Calendar.getInstance();

        // 获取秒数字段
        int seconds = (int) (calendar.getTimeInMillis() / 1000);
        TimeUnit.SECONDS.sleep(1);
        long millis = calendar.getTimeInMillis();
        // 输出时间戳
        System.out.println("当前秒的时间戳：" + seconds);
        System.out.println("当前毫秒的时间戳：" + millis);

        Assert.assertEquals(seconds, millis / 1000);
    }
}
