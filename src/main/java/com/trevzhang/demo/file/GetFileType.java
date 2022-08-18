package com.trevzhang.demo.file;

import java.io.*;
import java.util.HashMap;

/**
 * Java判断上传的文件格式
 */
public class GetFileType {

    public static void main(String[] args) {
        String path = "/Users/zcg/Pictures/临时图片/卡特琳娜岛.png";
        System.out.println("path:" + path);
        String type = getFileType(path);
        System.out.println("type:" + type);
        System.out.println("===");

        path = "/Users/zcg/Pictures/临时图片/上海飞汕头自由行.mp4";
        System.out.println("path:" + path);
        type = getFileType(path);
        System.out.println("type:" + type);
        System.out.println("===");

        path = "/Users/zcg/Pictures/临时图片/上海汕头自由行.jpg";
        System.out.println("path:" + path);
        type = getFileType(path);
        System.out.println("type:" + type);
        System.out.println("===");
    }
    // 缓存文件头信息-文件头信息

    public static final HashMap<String, String> mFileTypes = new HashMap<>();

    static {
        mFileTypes.put("FFD8FFE0", "jpg");
        mFileTypes.put("89504E47", "png");
        mFileTypes.put("424DC6CC", "bmp");
        mFileTypes.put("47494638", "gif");
        mFileTypes.put("00000020", "mp4");
    }

    /**
     * 根据文件路径获取文件头信息
     *
     * @param filePath 文件路径
     * @return 文件头信息
     */
    public static String getFileType(String filePath) {
        String type = getFileHeader(filePath);
        System.out.println("header:" + type);
        return mFileTypes.get(type);
    }

    /**
     * 根据文件路径获取文件头信息
     *
     * @param filePath 文件路径
     * @return 文件头信息
     */
    public static String getFileHeader(String filePath) {
        FileInputStream is = null;
        String value = null;
        try {
            is = new FileInputStream(filePath);
            byte[] buffer = new byte[4];
            /*
             * int read() 从此输入流中读取一个数据字节。 int read(byte[] buffer) 从此输入流中将最多 buffer.length
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] buffer, int off, int len)
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
             */
            is.read(buffer, 0, buffer.length);
            value = bytesToHexString(buffer);
        } catch (Exception ignore) {
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException ignore) {
                }
            }
        }
        return value;
    }

    /**
     * 将要读取文件头信息的文件的byte数组转换成string类型表示
     *
     * @param src 要读取文件头信息的文件的byte数组
     * @return 文件头信息
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (byte b : src) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(b & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }

}