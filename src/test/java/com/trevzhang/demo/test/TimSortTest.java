package com.trevzhang.demo.test;

import com.alibaba.fastjson.JSON;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * @author zhangchunguang.zcg
 * @since 2023/3/28 17:24
 */
public class TimSortTest {

    @Test
    public void testTimSort() {
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> dateList = new ArrayList<>();
        add(dateList, "2023-03-10", sdf);
        add(dateList, "2023-04-10", sdf);
        add(dateList, "2191-03-10", sdf);
        add(dateList, "2019-01-10 00:10:50", sdf);
        add(dateList, "2023-03-02", sdf);
        add(dateList, "2023-03-02", sdf);
        add(dateList, null, sdf);

        dateList.sort(Comparator.comparing(d->d));
        List<String > dateStrList = dateList.stream().map(d->sdf0.format(d)).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(dateStrList));
    }

    private static void add(List<Date> dateList, String source, SimpleDateFormat sdf) {
        try {
            dateList.add(sdf.parse(source));
        } catch (Exception e) {
            dateList.add(new Date());
        }
    }
}
