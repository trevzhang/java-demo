package com.trevzhang.demo.test;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author zhangchunguang.zcg
 * @since 2023/3/29 20:37
 */
public class ListTest {
    @Test
    public void test01() {
        List<Integer> list = Arrays.asList(new Integer[3]);
        System.out.println(list);
    }

    @Test
    public void testSubList() {
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < 1010; i++) {
            strList.add("" + i);
        }
        fixRoutePriceStatus(StringUtils.join(strList, ","));
    }

    public String fixRoutePriceStatus(String routeIdStr) {
        Preconditions.checkArgument(StringUtils.isNotBlank(routeIdStr), "线路ID列表为空");
        String[] routeIds = routeIdStr.split(",");
        if (routeIds.length == 0) {
            return "线路ID列表值为空";
        }
        List<String> routeIdList = Lists.newArrayList(routeIds);

        if (routeIdList.size() <= 100) {
            printIds(routeIdList);
        } else {
            for (int i = 0; i < (routeIdList.size() / 100)+1; i++) {
                System.out.println("i=" + i);
                printIds(routeIdList.subList(i * 100, Math.min((i + 1) * 100, routeIdList.size())));
            }
        }
        return "success";
    }

    private void printIds(List<String> subList) {
        for (int i = 0; i < subList.size(); i++) {
            System.out.print(subList.get(i) + " ");
        }
        System.out.println();
    }
}
