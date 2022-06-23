package com.trevzhang.demo.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 实参，形参测试
 * @author zhangchunguang.zcg
 * @since 2022/6/23 11:58 AM
 */
public class BeanTest {
    @Test
    public void test1() {
        List<StreamDemo.Bean<String>> beans = new ArrayList<>();
        beans.add(new StreamDemo.Bean("1"));
        beans.add(new StreamDemo.Bean("2"));
        beans.add(new StreamDemo.Bean("3"));
        // 值传递，传递的是指针
        filterList1(beans);
        System.out.println(beans);
    }

    @Test
    public void test2() {
        int i = 1;
        // 值传递，传递的是基本类型值，不改变原有变量值
        modify(i);
        System.out.println(i);
    }

    private void modify(int i) {
        i = i+1;
        i++;
    }

    private void filterList1(List<StreamDemo.Bean<String>> beans) {
        // 形参会开辟新的指针地址，这里是把新的列表(beans.stream())地址赋给了新的指针(beans)
        beans = beans.stream().filter(bean -> bean.getArg1().equals("1")).collect(Collectors.toList());
    }
}
