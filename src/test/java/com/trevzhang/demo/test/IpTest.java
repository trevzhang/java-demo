package com.trevzhang.demo.test;

import com.trevzhang.demo.util.IpUtils;
import org.junit.Test;

/**
 * @author Haruki
 * @since 2024/7/17 15:21
 */
public class IpTest {

    @Test
    public void testIpMask() {
        String originIp = "112.54.164.64/28";
        String[] ipSplit = originIp.split("/");
        System.out.println(IpUtils.parseIpMaskRange(ipSplit[0], ipSplit[1]));
    }
}
