package com.trevzhang.demo.util;

import java.nio.charset.StandardCharsets;
/**
 * 截断字符串，使其字节长度不超过指定长度
 *
 * @author haruki
 * @since 2024/10/14 16:33:18
 */
public class ByteTruncate {
    public static void main(String[] args) {
        String input = "SDK0827_bili_购买游戏标准版，赠送泡姆DLC1过长看看会怎能三安大厦阿大收到爱仕达啊爱仕达阿达啊萨达萨达阿达阿达爱仕达阿达碍事是的啊";
        int maxBytes = 127;

        String truncated = truncateToByteLength(input, maxBytes);
        System.out.println("原字符串: " + input);
        System.out.println("截断后字符串: " + truncated);
    }

    public static String truncateToByteLength(String input, int maxBytes) {
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        if (bytes.length <= maxBytes) {
            return input; // 不需要截断
        }

        // 找到最后一个完整的字符的字节索引
        int byteCount = 0;
        int charCount = 0;

        for (char c : input.toCharArray()) {
            byte[] charBytes = Character.toString(c).getBytes(StandardCharsets.UTF_8);
            if (byteCount + charBytes.length > maxBytes) {
                break;
            }
            byteCount += charBytes.length;
            charCount++;
        }

        // 返回截断后的字符串
        return input.substring(0, charCount);
    }
}
