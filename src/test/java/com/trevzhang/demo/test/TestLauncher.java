package com.trevzhang.demo.test;

/**
 * 测试Launcher加载路径
 * @see sun.misc.Launcher
 * @author zhangchunguang.zcg
 * @since 2022/3/7 10:28 AM
 */
public class TestLauncher {
    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println("---------");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println("---------");
        System.out.println(System.getProperty("java.class.path"));

    }
}
