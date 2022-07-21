package com.trevzhang.demo.test;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangchunguang.zcg
 * @since 2022/7/8 11:43 AM
 */
public class TimeDiffTest {
    @Test
    public void testTimeDiff() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf.parse("2022-07-08 10:32:40");
        Date d2 = sdf.parse("2022-07-08 10:36:42");

        long diff = DateUtil.between(d1, d2, DateUnit.SECOND);
        System.out.println(diff);
    }
}
