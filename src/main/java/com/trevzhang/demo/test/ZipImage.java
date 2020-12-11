package com.trevzhang.demo.test;

import cn.hutool.core.img.Img;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifDirectoryBase;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * 图片压缩
 * @author zhangchunguang
 */
public class ZipImage {

    /**
     * 压缩图片，得到缩略图
     *
     * @param file    原文件
     * @param width   缩略图宽
     * @param height  缩略图高
     * @param quality 图片质量
     * @throws IOException
     */
    public void createThumbPic(File file, int width, int height, float quality)
            throws IOException {
        //获得流
        String originalName = file.getName();
        Image srcFile = ImageIO.read(file);
        int oldWidth = srcFile.getWidth(null);
        int oldHeight = srcFile.getHeight(null);
        System.out.println("fileName: " + originalName + ", oldWidth: " + oldWidth + ", oldHeight: "
                + oldHeight);
        //宽高设定
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);
        FileOutputStream out = new FileOutputStream(
                new File("/Users/zhangchunguang/Pictures/zipDemo.jpg"));

//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//        JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
//        图片压缩质量
//        jep.setQuality(quality, true);
//        encoder.encode(tag, jep);
        out.close();
    }

    public void hutoolCreateThumbPic(String srcPath, String targetPath, float quality) {
        File file = new File(srcPath);
        File targetFile = new File(targetPath);
        Img.from(file).setQuality(quality).write(targetFile);
    }

    /**
     * 获取照片翻转角度
     * @param image
     * @return
     * @throws JpegProcessingException
     * @throws IOException
     * @throws MetadataException
     */
    public int getRotateAngleForPhoto(InputStream image)
            throws JpegProcessingException, IOException, MetadataException {
        Metadata metadata;
        int angle = 0;
        metadata = JpegMetadataReader.readMetadata(image);
        Directory directory = metadata.getFirstDirectoryOfType(ExifDirectoryBase.class);
        if (directory != null && directory.containsTag(ExifDirectoryBase.TAG_ORIENTATION)) {
            // Exif信息中方向　　
            int orientation = directory.getInt(ExifDirectoryBase.TAG_ORIENTATION);
            // 原图片的方向信息
            if (6 == orientation) {
                //6旋转90
                angle = 90;
            } else if (3 == orientation) {
                //3旋转180
                angle = 180;
            } else if (8 == orientation) {
                //8旋转90
                angle = 270;
            }
        }
        return angle;
    }

}
