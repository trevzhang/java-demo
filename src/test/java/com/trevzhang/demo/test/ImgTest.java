package com.trevzhang.demo.test;

import cn.hutool.core.img.Img;
import cn.hutool.core.io.FileUtil;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;

/**
 * @author zhangchunguang.zcg
 * @since 2023/7/20 17:37
 */
public class ImgTest {

    @Test
    public void testHutool() {
        Img.from(FileUtil.file("/Users/zcg/Pictures/临时图片/壁纸2.png"))
            .setQuality(0.1)//压缩比率
            .write(FileUtil.file("/Users/zcg/Pictures/临时图片/壁纸2-quality0.1.png"));
    }

    @Test
    public void testThumbnail() {
        compressPicForScale("/Users/zcg/Pictures/临时图片/壁纸.jpg", "/Users/zcg/Pictures/临时图片/壁纸-tb.jpg",
            1000, 0.8, 2560, 1440); // 图片小于1000kb
    }

    /**
     * 根据指定大小和指定精度压缩图片
     *
     * @param srcPath      源图片地址
     * @param desPath      目标图片地址
     * @param desFileSize  指定图片大小，单位kb（压缩到多大以内）
     * @param accuracy     精度，递归压缩的比率，建议小于0.9
     * @param desMaxWidth  目标最大宽度
     * @param desMaxHeight 目标最大高度
     * @return 目标文件路径
     */
    public static String compressPicForScale(String srcPath, String desPath, long desFileSize, double accuracy, int desMaxWidth, int desMaxHeight) {
        if (!new File(srcPath).exists()) {
            return null;
        }
        try {
            File srcFile = new File(srcPath);
            long srcFileSize = srcFile.length();
            System.out.println("源图片：" + srcPath + "，大小：" + srcFileSize / 1024 + "kb");
            //获取图片信息
            BufferedImage bim = ImageIO.read(srcFile);
            int srcWidth = bim.getWidth();
            int srcHeight = bim.getHeight();

            //先转换成jpg
            Thumbnails.Builder<File> builder = Thumbnails.of(srcFile).outputFormat("jpg");

            // 指定大小（宽或高超出会才会被缩放）
            if (srcWidth > desMaxWidth || srcHeight > desMaxHeight) {
                builder.size(desMaxWidth, desMaxHeight);
            } else {
                //宽高均小，指定原大小
                builder.size(srcWidth, srcHeight);
            }

            // 写入到内存
            ByteArrayOutputStream bos = new ByteArrayOutputStream(); //字节输出流（写入到内存）
            builder.toOutputStream(bos);

            // 递归压缩，直到目标文件大小小于desFileSize
            byte[] bytes = compressPicCycle(bos.toByteArray(), desFileSize, accuracy);

            // 输出到文件
            File desFile = new File(desPath);
            FileOutputStream fos = new FileOutputStream(desFile);
            fos.write(bytes);
            fos.close();

            System.out.println("目标图片：" + desPath + "，大小" + desFile.length() / 1024 + "kb");
            System.out.println("图片压缩完成！");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return desPath;
    }

    private static byte[] compressPicCycle(byte[] bytes, long desFileSize, double accuracy) throws IOException {
        // File srcFileJPG = new File(desPath);
        long srcFileSizeJPG = bytes.length;
        // 2、判断大小，如果小于500kb，不压缩；如果大于等于500kb，压缩
        if (srcFileSizeJPG <= desFileSize * 1024) {
            return bytes;
        }
        // 计算宽高
        BufferedImage bim = ImageIO.read(new ByteArrayInputStream(bytes));
        int srcWidth = bim.getWidth();
        int srcHeight = bim.getHeight();
        int desWidth = new BigDecimal(srcWidth).multiply(new BigDecimal(accuracy)).intValue();
        int desHeight = new BigDecimal(srcHeight).multiply(new BigDecimal(accuracy)).intValue();

        ByteArrayOutputStream bos = new ByteArrayOutputStream(); //字节输出流（写入到内存）
        Thumbnails.of(new ByteArrayInputStream(bytes)).size(desWidth, desHeight).outputQuality(accuracy).toOutputStream(bos);
        return compressPicCycle(bos.toByteArray(), desFileSize, accuracy);
    }

}
