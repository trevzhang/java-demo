package com.trevzhang.demo.test;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Haruki
 * @since 2025/2/20 11:32
 */
public class UtcInstantDemo {

    @Test
    public void test() {
        // 获取当前UTC0时间
        Instant orderRequestedAt = Instant.now();

        // 转换为ISO 8601格式（自动包含毫秒和Z）
        String formattedDate = orderRequestedAt.toString();

        // 输出类似2023-04-01T03:00:00.123Z
        System.out.println(formattedDate);
    }

    @Test
    public void test01() {
        String dateStr = "2023-04-01T03:00:00Z";
        Long defaultTimeMs = 0L;

        Long utc = getUTCWithDefault(dateStr, defaultTimeMs);

        // $ tst 1680318000000
        //时间戳位:	1680318000
        //本地时间:	2023-04-01 11:00:00
        System.out.println(utc);
    }

    /**
     * 将UTC时间字符串转换为毫秒数
     */
    public static Long getUTCWithDefault(String dateStr, Long defaultTimeMs) {
        if (dateStr == null || dateStr.isEmpty()) {
            return defaultTimeMs;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date date = sdf.parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            System.out.println("Error parsing date string: " + dateStr);
            e.printStackTrace();
        }
        return defaultTimeMs;
    }
}
