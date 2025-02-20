package com.trevzhang.demo.test;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

import static org.junit.Assert.*;

public class RsaPairGeneratorTest {
    private static final int KEY_SIZE = 2048;
    private static final String ALGORITHM = "RSA";
    private static final String SIGN_ALGORITHM = "SHA256withRSA";
    private static final String BODY = "This is a test message to be signed";

    // 生成密钥对（公用方法）
    private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyGen.initialize(KEY_SIZE);
        return keyGen.generateKeyPair();
    }

    // 测试用例1：生成签名
    @Test
    public void testGenerateSignature() throws Exception {
        KeyPair keyPair = generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("Public Key (Base64): " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("Private Key (Base64): " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        byte[] data = BODY.getBytes(StandardCharsets.UTF_8);

        // 生成签名
        Signature signer = Signature.getInstance(SIGN_ALGORITHM);
        signer.initSign(privateKey);
        signer.update(data);
        byte[] signature = signer.sign();

        assertNotNull("签名结果不应为空", signature);
        System.out.println("Signature (Base64): " + Base64.getEncoder().encodeToString(signature));
    }

    // 测试用例2：验证签名
    @Test
    public void testVerifySignature() throws Exception {
        KeyPair keyPair = generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        System.out.println("Public Key (Base64): " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("Private Key (Base64): " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        byte[] data = BODY.getBytes(StandardCharsets.UTF_8);

        // 生成签名
        Signature signer = Signature.getInstance(SIGN_ALGORITHM);
        signer.initSign(privateKey);
        signer.update(data);
        byte[] signature = signer.sign();
        System.out.println("Signature (Base64): " + Base64.getEncoder().encodeToString(signature));

        // 验证签名
        Signature verifier = Signature.getInstance(SIGN_ALGORITHM);
        verifier.initVerify(publicKey);
        verifier.update(data);
        boolean isValid = verifier.verify(signature);

        assertTrue("签名验证应通过", isValid);
    }

    // 新增验证失败场景（可选）
    @Test
    public void testVerifyTamperedSignature() throws Exception {
        KeyPair keyPair = generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("Public Key (Base64): " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("Private Key (Base64): " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        byte[] data = BODY.getBytes(StandardCharsets.UTF_8);

        // 生成篡改后的伪签名（例如随机字节）
        byte[] fakeSignature = new byte[256];
        new SecureRandom().nextBytes(fakeSignature);

        // 尝试验证
        Signature verifier = Signature.getInstance(SIGN_ALGORITHM);
        verifier.initVerify(publicKey);
        verifier.update(data);
        boolean isValid = verifier.verify(fakeSignature);

        assertFalse("篡改后的签名验证应失败", isValid);
    }
}