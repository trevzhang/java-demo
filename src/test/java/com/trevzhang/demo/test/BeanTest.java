package com.trevzhang.demo.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ʵ�Σ��ββ���
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
        // ֵ���ݣ����ݵ���ָ��
        filterList1(beans);
        System.out.println(beans);
    }

    @Test
    public void test2() {
        int i = 1;
        // ֵ���ݣ����ݵ��ǻ�������ֵ�����ı�ԭ�б���ֵ
        modify(i);
        System.out.println(i);
    }

    private void modify(int i) {
        i = i+1;
        i++;
    }

    private void filterList1(List<StreamDemo.Bean<String>> beans) {
        // �βλῪ���µ�ָ���ַ�������ǰ��µ��б�(beans.stream())��ַ�������µ�ָ��(beans)
        beans = beans.stream().filter(bean -> bean.getArg1().equals("1")).collect(Collectors.toList());
    }
}
