package com.trevzhang.demo.test;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.junit.Test;

/**
 * @author zhangchunguang.zcg
 * @since 2023/7/4 10:30
 */
public class IdUtilTest {

    @Test
    public void testSnowflake() {
        Snowflake snowflake1 = IdUtil.getSnowflake(1, 1);
        System.out.println("workerId:1, dataCenterId:1");
        for (int i = 0; i < 10; i++) {
            System.out.printf("i=%d, snowflakeId=%d%n", i, snowflake1.nextId());
        }

        Snowflake snowflake2 = IdUtil.getSnowflake(2, 2);
        System.out.println("workerId:2, dataCenterId:2");
        for (int i = 0; i < 10; i++) {
            System.out.printf("i=%d, snowflakeId=%d%n", i, snowflake2.nextId());
        }
    }
}
