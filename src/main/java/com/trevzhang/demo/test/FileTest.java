package com.trevzhang.demo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhangchunguang.zcg
 * @since 2021/10/27 5:26 下午
 */
public class FileTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("/Users/zcg/Pictures/archieve/1.sketch");
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = getBytesFromInputStream(fis);
        byte[] bytes1 = getBytesFromFile(file);
    }

    /**
     * 从inputStream获取字节数组
     *
     * @param input
     * @return
     * @throws IOException
     */
    public static byte[] getBytesFromInputStream(InputStream input) throws IOException {
        long start = System.currentTimeMillis();
        int length = input.available();
        if (length > Integer.MAX_VALUE) {
            // 文件太大，无法读取
            throw new IOException("File is to large");
        }
        // 创建一个数据来保存文件数据
        byte[] bytes = new byte[(int) length];// 读取数据到byte数组中
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = input.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file");
        }
        // Close the input stream and return bytes
        input.close();
        System.out.println("input length = " + length + ", read time = " + (System.currentTimeMillis() - start) + "ms");
        return bytes;
    }

    /**
     * 从file获取文件数组
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] getBytesFromFile(File file) throws IOException {
        long start = System.currentTimeMillis();
        InputStream is = new FileInputStream(file);// 获取文件大小
        long lengths = file.length();
        if (lengths > Integer.MAX_VALUE) {
            // 文件太大，无法读取
            throw new IOException("File is to large " + file.getName());
        }
        // 创建一个数据来保存文件数据
        byte[] bytes = new byte[(int) lengths];// 读取数据到byte数组中
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        // Close the input stream and return bytes
        is.close();
        System.out.println("file length = " + lengths + ", read time = " + (System.currentTimeMillis() - start) + "ms");
        return bytes;
    }
}
