package com.trevzhang.demo.test;

import org.junit.Test;

import java.time.Instant;

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
}
