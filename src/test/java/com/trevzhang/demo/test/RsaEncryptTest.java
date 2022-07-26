package com.trevzhang.demo.test;

import com.trevzhang.demo.util.RSAUtil;
import org.junit.Test;

import java.util.Map;

/**
 * RSA非对称加密解密测试
 *
 * @author zhangchunguang.zcg
 * @since 2022/7/21 8:56 PM
 */
public class RsaEncryptTest {
    /**
     * 公钥加密，私钥解密
     */
    @Test
    public void testEncryptByPublicKeyAndDecryptByPrivateKey() {
        Map<String, String> keyMap = RSAUtil.generateKeyPair();
        assert keyMap != null;
        String publicKey = keyMap.get(RSAUtil.PUBLIC_KEY);
        System.out.println("公钥：\n" + publicKey + "\n");
        String privateKey = keyMap.get(RSAUtil.PRIVATE_KEY);
        System.out.println("私钥：\n" + privateKey + "\n");

        String password = "abc123456HELLO_WORLD张春光";
        System.out.println("明文：\n" + password + "\n");

        for (int i = 1; i <= 3; i++) {
            System.out.printf("第%d次\n", i);
            String encryptStr = RSAUtil.encrypt(password, publicKey);
            System.out.println("公钥加密后：\n" + encryptStr + "\n");
            String decryptStr = RSAUtil.decrypt(encryptStr, privateKey);
            System.out.println("私钥解密后：\n" + decryptStr + "\n");
        }
    }

    /**
     * 私钥加密，公钥解密
     */
    @Test
    public void testEncryptByPrivateKeyAndDecryptByPublicKey() {
        Map<String, String> keyMap = RSAUtil.generateKeyPair();
        assert keyMap != null;
        String privateKey = keyMap.get(RSAUtil.PRIVATE_KEY);
        System.out.println("私钥：\n" + privateKey + "\n");
        String publicKey = keyMap.get(RSAUtil.PUBLIC_KEY);
        System.out.println("公钥：\n" + publicKey + "\n");

        String password = "abc123456HELLO_WORLD张春光";
        System.out.println("明文：\n" + password + "\n");
        for (int i = 1; i <= 3; i++) {
            System.out.println("===============");
            System.out.printf("第%d次\n", i);
            String encryptStr = RSAUtil.encryptByPrivateKey(password, privateKey);
            System.out.println("私钥加密后：\n" + encryptStr + "\n");
            String decryptStr = RSAUtil.decryptByPublicKey(encryptStr, publicKey);
            System.out.println("公钥解密后：\n" + decryptStr + "\n");
        }
    }

    /**
     * 公钥加密，公钥解密
     */
    @Test
    public void testEncryptByPublicKeyAndDecryptByPublicKey() {
        Map<String, String> keyMap = RSAUtil.generateKeyPair();
        assert keyMap != null;
        String publicKey = keyMap.get(RSAUtil.PUBLIC_KEY);
        System.out.println("公钥：\n" + publicKey + "\n");

        String password = "abc123456HELLO_WORLD张春光";
        System.out.println("明文：\n" + password + "\n");
        for (int i = 1; i <= 3; i++) {
            System.out.println("===============");
            System.out.printf("第%d次\n", i);
            String encryptStr = RSAUtil.encrypt(password, publicKey);
            System.out.println("公钥加密后：\n" + encryptStr + "\n");
            String decryptStr = RSAUtil.decryptByPublicKey(encryptStr, publicKey);
            System.out.println("公钥解密后：\n" + decryptStr + "\n");
        }
    }

    /**
     * 私钥加密，私钥解密
     */
    @Test
    public void testEncryptByPrivateKeyAndDecryptByPrivateKey() {
        Map<String, String> keyMap = RSAUtil.generateKeyPair();
        assert keyMap != null;
        String privateKey = keyMap.get(RSAUtil.PRIVATE_KEY);
        System.out.println("私钥：\n" + privateKey + "\n");

        String password = "abc123456HELLO_WORLD张春光";
        System.out.println("明文：\n" + password + "\n");
        for (int i = 1; i <= 3; i++) {
            System.out.println("===============");
            System.out.printf("第%d次\n", i);
            String encryptStr = RSAUtil.encryptByPrivateKey(password, privateKey);
            System.out.println("私钥加密后：\n" + encryptStr + "\n");
            String decryptStr = RSAUtil.decrypt(encryptStr, privateKey);
            System.out.println("私钥解密后：\n" + decryptStr + "\n");
        }
    }
}
