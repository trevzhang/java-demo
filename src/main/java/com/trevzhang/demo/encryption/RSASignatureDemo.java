package com.trevzhang.demo.encryption;

import java.security.*;
import java.util.Base64;

/**
 * RSA私钥签名，公钥验证签名
 *
 * @author Haruki
 * @since 2025/1/15 16:51
 */
public class RSASignatureDemo {

    public static void main(String[] args) throws Exception {
        // 生成RSA密钥对
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // 要签名的消息
        String message = "This is a confidential message from the merchant to Waffo.";

        // 签名消息
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        byte[] signedMessage = signature.sign();

        // 将签名转换为Base64编码的字符串
        String encodedSignature = Base64.getEncoder().encodeToString(signedMessage);
        System.out.println("Signature: " + encodedSignature);

        // 验证签名
        Signature signatureVerify = Signature.getInstance("SHA256withRSA");
        signatureVerify.initVerify(publicKey);
        signatureVerify.update(message.getBytes());
        boolean isVerified = signatureVerify.verify(signedMessage);

        // 输出验证结果
        if (isVerified) {
            System.out.println("Signature is valid.");
        } else {
            System.out.println("Signature is invalid.");
        }
    }
}
