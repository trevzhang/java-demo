package com.trevzhang.demo.struct.redBlack;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Trevor Zhang
 * @since 2020/10/30
 */
public class TestRBTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---红黑树模拟插入程序---");
        System.out.println("请输入key的类型: 1.String 2.int");
        String type = sc.next();
        while (!Arrays.asList("1", "2").contains(type)) {
            System.out.println("输入有误, 请重新输入:");
            type = sc.next();
        }
        boolean stringType = type.equals("1");
        RBTree rbt;
        if (stringType) {
            rbt = new RBTree<String, Object>();
        } else {
            rbt = new RBTree<Integer, Object>();
        }
        //测试输入：ijkgefhdabc
        while(true) {
            System.out.println("请输入key:");
            if (stringType) {
                String key = sc.next();
                rbt.insert(key, null);
            } else {
                int key = sc.nextInt();
                rbt.insert(key, null);
            }

            RBTreeOperation.show(rbt.getRoot());
        }
    }
}
