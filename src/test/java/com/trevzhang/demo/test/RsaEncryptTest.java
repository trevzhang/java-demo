package com.trevzhang.demo.test;

import com.trevzhang.demo.util.RSAUtil;
import com.trevzhang.demo.util.RSAUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @author zhangchunguang.zcg
 * @since 2022/7/21 8:56 PM
 */
public class RsaEncryptTest {

    @Test
    public void TestHutoolEncrypt() {
        Map<String, String> keyMap = RSAUtil.generateKeyPair();
        assert keyMap != null;
        String publicKey = keyMap.get(RSAUtil.PUBLIC_KEY);
        System.out.println("公钥：\n" + publicKey + "\n");
        String privateKey = keyMap.get(RSAUtil.PRIVATE_KEY);
        System.out.println("私钥：\n" + privateKey + "\n");

        String password = "abc123456HELLO_WORLD张春光";
        System.out.println("明文：\n" + password + "\n");
        String encryptStr = RSAUtil.encrypt(password, publicKey);
        System.out.println("公钥加密后：\n" + encryptStr + "\n");
        String decryptStr = RSAUtil.decrypt(encryptStr, privateKey);
        System.out.println("私钥解密后：\n" + decryptStr + "\n");
    }

    @Test
    public void testDecrypt() {
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK+7RvPYPA2d6H+JwnMrCXOlRQo/\n" +
                "Lzi3naNG6n878WBRNgKwcOxR+WhW556P22PMFKWEHjFN9FQ6Fd2OtkPPmu0dT19pzlXpFX4NEu0n\n" +
                "vOx3Pjw2EAI+aiLA5CNfwBFq+Ps+V9ZhVwom40NpZBsBce7RAb++wiKGZtFURJKTJEMLAgMBAAEC\n" +
                "gYAVz2VXIUi+xeY+Yf90t2wvuAeGuzXQpmqrAkOwsToGzWgp0vKI71mdhgpZfHBAWsAF6jesazOT\n" +
                "pTaOg3ECnzMSiFw7neQ5kpl84Eg8o2dy0Q1ZnhpPQVGvgHKBy+59PsR0OyVrJ7qYe5WNqlgoWHxs\n" +
                "IcxOnQqqC34sWfsmoBrr0QJBANyCUNKZsEy27vYeZ9Lu813ardvOIYGtEGD1mD9t2asAKwHOqxiE\n" +
                "JK6AajSyUfcij/8UavoiQpwc6Zrfa12v/xECQQDMA/vy/ha6DV0HIYUYlSVfMRqe70vDlf4bXH+1\n" +
                "ZDsT9+aXCHhE6cgKygK6Uhg/zneRJWYOmBvF1eYajGWw+xhbAkBPfkM4yOL/pOscmvV+21msugr3\n" +
                "NKZro0JGUgNiym0v8k2OxzxzCCBZMD4ZZTebSkBzCp9OMU+mC95DUMvZ6HqhAkBzIjh4jE5VFx+N\n" +
                "26vaEKlzjCmi0hbmvOoXlN75hiQVA8zdJUmCUpCzDgf2EEhhEerGPd3XlJ3vyuO9zcfB1iKLAkEA\n" +
                "xrOoIjrvwnM3OAGbuGwYAUKo9/Ku2s8X8f9nAziDEKKHbkUVd1AUDGPfUclxMoggydA+bpST8oSy\n" +
                "FUAeLeygGQ==";
        String encryptedData = "";
        String decryptStr = RSAUtil.decrypt(encryptedData, privateKey);
        System.out.println(decryptStr);
    }

    @Deprecated
    private Pair<RSAPublicKey, RSAPrivateKey> generatePublicKeyAndPrivateKey() throws Exception {
        KeyPair keyPair = RSAUtils.generateKeyPair();
        // 生成公钥和私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 私钥保存在session中，用于解密
        BigInteger privateKeyExponent = privateKey.getPrivateExponent();
        // 公钥信息保存在页面，用于加密
        String publicKeyExponent = publicKey.getPublicExponent().toString(16);
        String publicKeyModulus = publicKey.getModulus().toString(16);

        System.out.println("privateKeyExponent: " + privateKeyExponent);
        System.out.println("publicKeyExponent: " + publicKeyExponent);
        System.out.println("publicKeyModulus: " + publicKeyModulus);
        return Pair.of(publicKey, privateKey);
    }

    @Test
    public void testEncrypt() throws Exception {
        String password = "abc123456";
        Pair<RSAPublicKey, RSAPrivateKey> keyPair = generatePublicKeyAndPrivateKey();
        RSAPublicKey publicKey = keyPair.getLeft();
    }
}
