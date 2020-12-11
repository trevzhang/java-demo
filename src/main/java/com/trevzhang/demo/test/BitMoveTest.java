package com.trevzhang.demo.test;

/**
 * 位运算的左右移动测试
 *
 * @author Trevor Zhang
 * @since 2020/10/25 5:38 下午
 */
public class BitMoveTest {

    /**
     * 对于负数来说 <br> >>:  右移的同时, 在高位补1; <br> >>>: 逻辑右移, 不管高位, 右移后符号位会变为0
     *
     * @param args
     */
    public static void main(String[] args) {
        int a = -2048;
        int b = a >>> 4;
        System.out.println("a:");
        System.out.println(Integer.toBinaryString(a));
        System.out.println(a);
        System.out.println("b:");
        System.out.println(Integer.toBinaryString(b));
        System.out.println(b);
    }
}
