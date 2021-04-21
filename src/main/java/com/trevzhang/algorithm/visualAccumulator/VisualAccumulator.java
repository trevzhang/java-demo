package com.trevzhang.algorithm.visualAccumulator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 春火
 * @since 2021/4/15 4:08 下午
 */
public class VisualAccumulator {
    private double total;
    private int N;

    interface Bean {

    }

    class SubBean implements Bean {

    }

    public void test(String[] args) {
        Bean bean = new SubBean();
        Bean bean1 = new SubBean();
        SubBean bean2 = new SubBean();
        List<Bean> list = new ArrayList<>();
        list.add(bean);
        list.add(bean1);
        list.add(bean2);
    }
}
