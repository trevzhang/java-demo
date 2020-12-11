package com.trevzhang.demo.test;

/**
 * @author Trevor Zhang
 * @since 2020/10/19 1:03 下午
 */
public class PoolTest {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    public static void main(String[] args) {
        System.out.println("COUNT_BITS: " + Integer.toBinaryString(COUNT_BITS));
        System.out.println("CAPACITY:   " + Integer.toBinaryString(CAPACITY));
        System.out.println("RUNNING:    " +  Integer.toBinaryString(RUNNING));
        System.out.println("SHUTDOWN:   " + Integer.toBinaryString(SHUTDOWN));
        System.out.println("STOP:       00" + Integer.toBinaryString(STOP));
        System.out.println("TIDYING:    0" + Integer.toBinaryString(TIDYING));
        System.out.println("TERMINATED: 0" + Integer.toBinaryString(TERMINATED));
    }
}
