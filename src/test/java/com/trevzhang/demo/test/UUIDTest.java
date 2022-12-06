package com.trevzhang.demo.test;

import java.text.DecimalFormat;
import java.util.UUID;
import org.junit.Test;

/**
 * @author 春火
 * @since 2021/3/4 2:24 下午
 */
public class UUIDTest {
    private static final String STR_FORMAT = "000000000000";

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }

    @Test
    public void testGenerate() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }
}
