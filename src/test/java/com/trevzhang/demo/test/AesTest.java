package com.trevzhang.demo.test;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.DES;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Haruki
 * @since 2024/9/9 20:24
 */
public class AesTest {

    @Test
    public void test01() {
        String original = "05dac08f17c5da69b5eaaac4e70f141b_sh";
        // 使用 16 字节的密钥
        String secretKey = "1234567890123456"; // 16 字节

        AES aes = SecureUtil.aes(secretKey.getBytes());
        String encrypt = aes.encryptHex(original);
        System.out.println(encrypt);

        byte[] decrypt = aes.decrypt(encrypt);
        System.out.println(new String(decrypt));

        assertEquals(original, new String(decrypt));
    }
}
