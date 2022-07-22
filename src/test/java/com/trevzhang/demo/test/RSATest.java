package com.trevzhang.demo.test;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PrivateKeyInfoFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.crypto.util.SubjectPublicKeyInfoFactory;
import org.junit.Test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.*;

@Deprecated
public class RSATest {
    private static final Base64.Encoder ENCODER_64 = Base64.getEncoder();

    @Test
    public void testEncryptAndDecrypt() throws Exception {
        //生成密钥对
        RSAKeyPairGenerator rsaKeyPairGenerator = new RSAKeyPairGenerator();
        RSAKeyGenerationParameters rsaKeyGenerationParameters =
                new RSAKeyGenerationParameters(BigInteger.valueOf(3), new SecureRandom(), 1024, 25);
        //初始化参数
        rsaKeyPairGenerator.init(rsaKeyGenerationParameters);
        AsymmetricCipherKeyPair keyPair = rsaKeyPairGenerator.generateKeyPair();
        //公钥
        AsymmetricKeyParameter publicKey = keyPair.getPublic();
        //私钥
        AsymmetricKeyParameter privateKey = keyPair.getPrivate();
        SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(publicKey);
        PrivateKeyInfo privateKeyInfo = PrivateKeyInfoFactory.createPrivateKeyInfo(privateKey);
        //变字符串
        ASN1Object asn1ObjectPublic = subjectPublicKeyInfo.toASN1Primitive();
        byte[] publicInfoByte = asn1ObjectPublic.getEncoded();
        ASN1Object asn1ObjectPrivate = privateKeyInfo.toASN1Primitive();
        byte[] privateInfoByte = asn1ObjectPrivate.getEncoded();
        //这里可以将密钥对保存到本地
        System.out.println("PublicKey: \n" + ENCODER_64.encodeToString(publicInfoByte));
        System.out.println("PrivateKey: \n" + ENCODER_64.encodeToString(privateInfoByte));

        //加密、解密
//        ASN1Object pubKeyObj = subjectPublicKeyInfo.toASN1Primitive();//这里也可以从流中读取，从本地导入
        AsymmetricKeyParameter pubKey = PublicKeyFactory.createKey(SubjectPublicKeyInfo.getInstance(asn1ObjectPublic));
        AsymmetricBlockCipher cipher = new RSAEngine();
        cipher.init(true, pubKey);//true 表示加密
        //加密
        String data = "abc123456HELLO_WORLD张春光";
        System.out.println("\n 明文： " + data);
        byte[] encryptData = cipher.processBlock(data.getBytes(UTF_8), 0, data.getBytes(UTF_8).length);
        System.out.println("密文:" + ENCODER_64.encodeToString(encryptData));
        //解密
        AsymmetricKeyParameter priKey = PrivateKeyFactory.createKey(PrivateKeyInfo.getInstance(asn1ObjectPrivate));
        cipher.init(false, priKey);//false 表示解密
        byte[] decryptData = cipher.processBlock(encryptData, 0, encryptData.length);
        String decryptStr = new String(decryptData, UTF_8);
        System.out.println("解密后数据： " + decryptStr);
    }
}
