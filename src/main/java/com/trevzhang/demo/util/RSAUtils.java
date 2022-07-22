package com.trevzhang.demo.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;

/**
 * ���ܹ�����
 * @author zhangchunguang.zcg
 * @since 2022/7/21 8:58 PM
 */
@Deprecated
public class RSAUtils {

    /**
     * * ������Կ�� *
     *
     * @return KeyPair *
     * @throws Exception
     */
    public static KeyPair generateKeyPair() throws Exception {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", new BouncyCastleProvider());
            //��С
            final int KEY_SIZE = 1024;
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return keyPair;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * * ���� *
     *
     * @param privateKey ���ܵ���Կ *
     * @param raw        �Ѿ����ܵ����� *
     * @return ���ܺ������ *
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public static byte[] decrypt(PrivateKey privateKey, byte[] raw) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            int j = 0;
            while (raw.length - j * blockSize > 0) {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
                j++;
            }
            return bout.toByteArray();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * ���ܷ���
     *
     * @param paramStr
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String decryptStr(String paramStr, PrivateKey privateKey) throws Exception {
        byte[] en_result = hexStringToBytes(paramStr);
        byte[] de_result = decrypt(privateKey, en_result);
        StringBuffer sb = new StringBuffer();
        sb.append(new String(de_result, "UTF-8"));
        //���ؽ��ܵ��ַ���
        return sb.reverse().toString();
    }


    /**
     * 16���� To byte[]
     *
     * @param hexString
     * @return byte[]
     */
    private static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

}