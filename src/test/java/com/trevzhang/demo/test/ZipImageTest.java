package com.trevzhang.demo.test;

import cn.hutool.core.io.FileUtil;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.MetadataException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import org.junit.Before;
import org.junit.Test;

public class ZipImageTest {

    ZipImage zipImage;

    @Before
    public void init() {
        this.zipImage = new ZipImage();
    }

    @Test
    public void testThumbnailator() throws IOException {
        String srcPath = "/Users/zhangchunguang/Pictures/IMG_2007.jpeg";
        String targetPath = "/Users/zhangchunguang/Pictures/zipDemo.jpeg";
        float quality = 0.25f;
        File srcFile = new File(srcPath);
        Thumbnails.of(srcFile).scale(1f).outputQuality(quality).outputFormat("jpg").toFile(targetPath);
    }

    @Test
    public void testGetRotateAngle() throws JpegProcessingException, MetadataException, IOException {
        String srcPath = "/Users/zhangchunguang/Pictures/IMG_2007.jpeg";
        File srcFile = new File(srcPath);
        InputStream in = FileUtil.getInputStream(srcFile);
        int angle = zipImage.getRotateAngleForPhoto(in);
        System.out.println(angle);
    }

    @Test
    public void testHutoolImg() {
        String srcPath = "/Users/zhangchunguang/Pictures/IMG_2007.jpeg";
        String targetPath = "/Users/zhangchunguang/Pictures/zipDemo.jpeg";
        zipImage.hutoolCreateThumbPic(srcPath, targetPath, 0.25f);
    }

    /**
     * java自带的图片压缩方式
     *
     * @throws IOException
     */
    @Test
    public void testImageIO() throws IOException {
        File srcFile = new File("/Users/zhangchunguang/Pictures/IMG_2007.jpeg");
        Image srcImage = ImageIO.read(srcFile);
        int width = srcImage.getWidth(null);
        int height = srcImage.getHeight(null);
        float quality = 0.25f;

        zipImage.createThumbPic(srcFile, width, height, quality);
        System.out.println("success");
    }
}
