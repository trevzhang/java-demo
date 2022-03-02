package com.trevzhang.demo.test;

import cn.hutool.core.date.DateField;
import cn.hutool.core.util.RandomUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * 列表排序工具
 *
 * @author zhangchunguang.zcg
 * @since 2022/3/2 3:08 PM
 */
public class CompareTest {
    public static void main(String[] args) {
        List<CompareBean> rawList = generateList();
        System.out.println(rawList);
        rawList.sort(Comparator.comparing(CompareBean::getDate));
        System.out.println(rawList);
    }

    private static List<CompareBean> generateList() {
        List<CompareBean> compareBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CompareBean compareBean = new CompareBean();
            compareBean.setDate(RandomUtil.randomDate(new Date(), DateField.HOUR, 1, 12));
            compareBeanList.add(compareBean);
        }
        return compareBeanList;
    }

    @Data
    static class CompareBean implements Comparable<CompareBean> {
        private Date date;

        @Override
        public int compareTo(CompareBean o) {
            return 0;
        }

    }
}
