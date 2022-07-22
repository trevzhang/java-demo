package com.trevzhang.demo.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import sun.misc.BASE64Encoder;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangchunguang.zcg
 * @since 2022/7/22 2:45 PM
 */
public class RSAUtil {
    /**
     * ����
     */
    public static final String ENCRYPT_TYPE = "RSA";
    /**
     * ��ȡ��Կ��key
     */
    public static final String PUBLIC_KEY = "RSAPublicKey";
    /**
     * ��ȡ˽Կ��key
     */
    public static final String PRIVATE_KEY = "RSAPrivateKey";
    /**
     * ��Կ����
     *
     * @param content �����ܵ�����
     * @param publicKey ��Կ
     * @return ���ܺ������
     */
    public static String encrypt(String content, PublicKey publicKey) {
        try {
            RSA rsa = new RSA(null, publicKey);
            return rsa.encryptBase64(content, KeyType.PublicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��Կ����
     *
     * @param content �����ܵ�����
     * @param publicKey ��Կ
     * @return ���ܺ������
     */
    public static String encrypt(String content, String publicKey) {
        try {
            RSA rsa = new RSA(null, publicKey);
            return rsa.encryptBase64(content, KeyType.PublicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * ˽Կ����
     *
     * @param content �����ܵ�����
     * @param privateKey ˽Կ
     * @return ���ܺ������
     */
    public static String decrypt(String content, PrivateKey privateKey) {
        try {
            RSA rsa = new RSA(privateKey, null);
            return rsa.decryptStr(content, KeyType.PrivateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ˽Կ����
     *
     * @param content �����ܵ�����
     * @param privateKey ˽Կ
     * @return ���ܺ������
     */
    public static String decrypt(String content, String privateKey) {
        try {
            RSA rsa = new RSA(privateKey, null);
            return rsa.decryptStr(content, KeyType.PrivateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ȡ��˽Կ-���ȡһ�κ󱣴湫˽Կʹ��
     *
     * @return ��Կ��˽Կ
     */
    public static Map<String, String> generateKeyPair() {
        try {
            KeyPair pair = SecureUtil.generateKeyPair(ENCRYPT_TYPE);
            PublicKey publicKey = pair.getPublic();
            PrivateKey privateKey = pair.getPrivate();

            byte[] pubEncBytes = publicKey.getEncoded();
            byte[] priEncBytes = privateKey.getEncoded();
            // �ѹ�Կ��˽Կת��ΪBase64��ʽ
            String pubEncBase64 = new BASE64Encoder().encode(pubEncBytes);
            String priEncBase64 = new BASE64Encoder().encode(priEncBytes);

            Map<String, String> map = new HashMap<String, String>(2);
            map.put(PUBLIC_KEY, pubEncBase64);
            map.put(PRIVATE_KEY, priEncBase64);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
