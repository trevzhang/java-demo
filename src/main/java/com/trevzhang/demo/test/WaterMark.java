package com.trevzhang.demo.test;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.lang.UUID;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;

/**
 * @author Trevor Zhang
 * @date 2020/9/18 2:38 下午
 */
public class WaterMark {

    public static void main(String[] args) {
        File srcFile = new File("/Users/zhangchunguang/Pictures/表情/shenfen1.jpeg");
        File destFile = new File(
            "/Users/zhangchunguang/Pictures/表情/shenfen1-" + UUID.fastUUID() + ".jpeg");
        BufferedImage srcImage = ImgUtil.read(srcFile);
        int fontHeight = BigDecimal.valueOf(srcImage.getHeight())
            .multiply(new BigDecimal("0.1")).intValue();
        Font font = new Font("宋体", Font.BOLD, fontHeight);
        ImgUtil.pressText(srcImage, destFile, "仅 作 清 关 用 途", Color.GRAY, font, 0, 0, 1.0f);
    }
}
