package com.trevzhang.demo.test;

import java.net.InetAddress;

/**
 * 测试Java网络工具类
 * @author zhangchunguang.zcg
 * @since 2022/12/14 10:37 AM
 */
public class TestInet {

    public static void main(String[] args) throws Exception {
        // 获取本机 InetAddress 对象
        InetAddress localHost = InetAddress.getLocalHost();
        printInetAddress(localHost);
        // 获取指定域名 InetAddress 对象
        InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
        printInetAddress(inetAddress);
    }

    public static void printInetAddress(InetAddress inetAddress) {
        System.out.println("InetAddress：" + inetAddress);
        System.out.println("主机名：" + inetAddress.getHostName());
        System.out.println("IP地址：" + inetAddress.getHostAddress());
    }
}
