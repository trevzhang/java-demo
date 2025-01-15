package com.trevzhang.demo.encryption;

import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

/**
 * RSA公钥加密数据，私钥解密数据
 *
 * @author Haruki
 * @since 2025/1/15 16:53
 */
public class RSAEncryptionDemo {

    public static void main(String[] args) throws Exception {
        // 生成RSA密钥对
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // 要加密的消息
        String message = "This is a secret message.";

        // 加密消息
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMessage = encryptCipher.doFinal(message.getBytes());

        // 将加密后的消息转换为Base64编码的字符串
        String encodedEncryptedMessage = Base64.getEncoder().encodeToString(encryptedMessage);
        System.out.println("Encrypted Message: " + encodedEncryptedMessage);

        // 解密消息
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessage = decryptCipher.doFinal(encryptedMessage);

        // 输出解密后的消息
        String decryptedText = new String(decryptedMessage);
        System.out.println("Decrypted Message: " + decryptedText);
    }
}
