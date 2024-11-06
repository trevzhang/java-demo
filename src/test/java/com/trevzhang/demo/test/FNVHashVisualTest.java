package com.trevzhang.demo.test;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.axis.NumberAxis;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
/**
 * @author Haruki
 * @since 2024/11/6 19:56
 */
public class FNVHashVisualTest extends JFrame {
    private static class HashResult {
        double coverage;
        double avgCollisions;
        int maxCollisions;
        long totalCollisions;
        Map<Integer, Integer> distribution;

        public HashResult() {
            this.distribution = new HashMap<>();
        }
    }

    // FNV-1a 实现（保持不变）
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

    // 非素数变体实现（保持不变）
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

    private static HashResult calculateMetrics(List<String> testData, boolean isStandard, int bucketSize) {
        HashResult result = new HashResult();
        Set<Integer> uniqueHashes = new HashSet<>();

        // 计算散列值并统计分布
        for (String s : testData) {
            int hash = (isStandard ? standardFNV(s) : nonPrimeFNV(s)) % bucketSize;
            result.distribution.put(hash, result.distribution.getOrDefault(hash, 0) + 1);
            uniqueHashes.add(hash);
        }

        // 计算指标
        result.maxCollisions = Collections.max(result.distribution.values());
        result.totalCollisions = result.distribution.values().stream()
                .filter(v -> v > 1)
                .mapToLong(v -> v - 1)
                .sum();

        result.avgCollisions = (double) result.totalCollisions / result.distribution.size();
        result.coverage = (double) uniqueHashes.size() / bucketSize * 100;

        return result;
    }

    public static void createAndShowCharts(List<String> testData, int[] bucketSizes) {
        JFrame frame = new JFrame("FNV Hash 分析结果 (数据集大小:" + testData.size() + ")");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 2));

        // 创建数据集
        DefaultCategoryDataset coverageDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset collisionsDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset maxCollisionsDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset totalCollisionsDataset = new DefaultCategoryDataset();

        // 收集数据
        for (int bucketSize : bucketSizes) {
            HashResult standardResult = calculateMetrics(testData, true, bucketSize);
            HashResult nonPrimeResult = calculateMetrics(testData, false, bucketSize);

            String bucketLabel = "桶大小-" + bucketSize;

            coverageDataset.addValue(standardResult.coverage, "标准FNV", bucketLabel);
            coverageDataset.addValue(nonPrimeResult.coverage, "非素数FNV", bucketLabel);

            collisionsDataset.addValue(standardResult.avgCollisions, "标准FNV", bucketLabel);
            collisionsDataset.addValue(nonPrimeResult.avgCollisions, "非素数FNV", bucketLabel);

            maxCollisionsDataset.addValue(standardResult.maxCollisions, "标准FNV", bucketLabel);
            maxCollisionsDataset.addValue(nonPrimeResult.maxCollisions, "非素数FNV", bucketLabel);

            totalCollisionsDataset.addValue(standardResult.totalCollisions, "标准FNV", bucketLabel);
            totalCollisionsDataset.addValue(nonPrimeResult.totalCollisions, "非素数FNV", bucketLabel);
        }

        // 创建图表
        frame.add(createChartPanel("覆盖率 (%)", coverageDataset));
        frame.add(createChartPanel("平均碰撞数", collisionsDataset));
        frame.add(createChartPanel("最大碰撞数", maxCollisionsDataset));
        frame.add(createChartPanel("总碰撞数", totalCollisionsDataset));

        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static ChartPanel createChartPanel(String title, DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                title,
                "桶大小",
                "值",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // 设置图表样式
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(79, 129, 189));
        renderer.setSeriesPaint(1, new Color(192, 80, 77));

        // 设置Y轴
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return new ChartPanel(chart);
    }

    public static void main(String[] args) {
        // 生成测试数据
        List<String> testData = new ArrayList<>();
        Random random = new Random();

        // 生成1000个随机字符串
        for (int i = 0; i < 1000; i++) {
            StringBuilder sb = new StringBuilder();
            int length = 5 + random.nextInt(10);
            for (int j = 0; j < length; j++) {
                sb.append((char) ('a' + random.nextInt(26)));
            }
            testData.add(sb.toString());
        }

        // 添加一些数字字符串和相似字符串
        for (int i = 0; i < 500; i++) {
            testData.add(String.valueOf(random.nextInt(10000)));
        }
        for (int i = 1; i <= 10; i++) {
            testData.add("user" + i);
            testData.add("test" + i);
        }

        // 测试不同桶大小
        int[] bucketSizes = {100, 1000, 10000};

        // 创建并显示图表
        SwingUtilities.invokeLater(() -> createAndShowCharts(testData, bucketSizes));
    }
}
