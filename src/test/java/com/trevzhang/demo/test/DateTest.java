package com.trevzhang.demo.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

/**
 * 日期测试
 *
 * @author 春火
 * @since 2021/1/28 1:57 下午
 */
public class DateTest {

    @Test
    public void testDateCeiling() {
        Date now = new Date();
        Date dateAfterHalfYear = DateUtils.ceiling(DateUtils.addDays(now, 180), Calendar.DATE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(dateAfterHalfYear));
    }
    @Test
    public void testStringBuilder() {
        Date testDate = new Date(116, 3, 5);
        System.out.println(testDate);
    }
}
