package com.trevzhang.demo.test;

import java.util.*;
/**
 * @author Haruki
 * @since 2024/11/6 19:46
 */
public class FNVHashTest {
    // 标准 FNV-1a
    private static int standardFNV(String key) {
        final int FNV_PRIME = 0x01000193;
        final int OFFSET_BASIS = 0x7ee36237;

        int hash = OFFSET_BASIS;
        for (char c : key.toCharArray()) {
            hash ^= c;
            hash *= FNV_PRIME;
        }
        return hash & 0x7FFFFFFF;
    }

    // 使用非素数的变体
    private static int nonPrimeFNV(String key) {
        final int NON_PRIME = 0x01000190;
        final int OFFSET_BASIS = 0x7ee36237;

        int hash = OFFSET_BASIS;
        for (char c : key.toCharArray()) {
            hash ^= c;
            hash *= NON_PRIME;
        }
        return hash & 0x7FFFFFFF;
    }

    // 生成测试数据
    private static List<String> generateTestData(int size) {
        List<String> data = new ArrayList<>();
        // 1. 随机字符串
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();
            int length = 5 + random.nextInt(10); // 5-15位长度
            for (int j = 0; j < length; j++) {
                sb.append((char) ('a' + random.nextInt(26)));
            }
            data.add(sb.toString());
        }
        // 2. 数字字符串
        for (int i = 0; i < size/2; i++) {
            data.add(String.valueOf(random.nextInt(10000)));
        }
        // 3. 相似字符串
        data.add("user1");
        data.add("user2");
        data.add("user3");
        data.add("test1");
        data.add("test2");
        data.add("test3");
        return data;
    }

    // 计算统计指标
    private static void calculateMetrics(List<String> testData, boolean isStandard, int bucketSize) {
        Map<Integer, Integer> distribution = new HashMap<>();
        Set<Integer> uniqueHashes = new HashSet<>();

        // 计算散列值并统计分布
        for (String s : testData) {
            int hash = (isStandard ? standardFNV(s) : nonPrimeFNV(s)) % bucketSize;
            distribution.put(hash, distribution.getOrDefault(hash, 0) + 1);
            uniqueHashes.add(hash);
        }

        // 计算指标
        int maxCollisions = Collections.max(distribution.values());
        long totalCollisions = distribution.values().stream()
                .filter(v -> v > 1)
                .mapToLong(v -> v - 1)
                .sum();

        double avgCollisions = (double) totalCollisions / distribution.size();
        double coverage = (double) uniqueHashes.size() / bucketSize * 100;

        // 输出结果
        System.out.println("\n" + (isStandard ? "标准 FNV-1a" : "非素数 FNV") + " 统计结果：");
        System.out.println("总数据量: " + testData.size());
        System.out.println("桶大小: " + bucketSize);
        System.out.println("唯一散列值数量: " + uniqueHashes.size());
        System.out.println("覆盖率: " + String.format("%.2f%%", coverage));
        System.out.println("最大碰撞数: " + maxCollisions);
        System.out.println("平均碰撞数: " + String.format("%.2f", avgCollisions));
        System.out.println("总碰撞数: " + totalCollisions);

        // 输出碰撞详情
        System.out.println("\n碰撞详情：");
        distribution.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(10)
                .forEach(e -> System.out.println("散列值 " + e.getKey() + ": " + e.getValue() + " 次"));
    }

    public static void main(String[] args) {
        // 生成测试数据
        List<String> testData = generateTestData(1000);

        // 测试不同桶大小
        int[] bucketSizes = {100, 1000, 10000};

        for (int bucketSize : bucketSizes) {
            System.out.println("\n========== 桶大小: " + bucketSize + " ==========");
            calculateMetrics(testData, true, bucketSize);
            calculateMetrics(testData, false, bucketSize);
        }

        // 输出部分原始数据的散列值对比
        System.out.println("\n========== 样本数据散列值对比 ==========");
        List<String> sampleData = testData.subList(0, Math.min(10, testData.size()));
        for (String s : sampleData) {
            System.out.printf("%s:\n\t标准FNV: %d\n\t非素数: %d\n",
                    s, standardFNV(s) % 100, nonPrimeFNV(s) % 100);
        }
    }
}
