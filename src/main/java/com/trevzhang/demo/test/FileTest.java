package com.trevzhang.demo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author zhangchunguang.zcg
 * @since 2021/10/27 5:26 下午
 */
public class FileTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("/Users/zcg/Pictures/1.jpeg");
        File outFile = new File("/Users/zcg/Pictures/" + UUID.randomUUID() + ".jpeg");
        copyFile(file, outFile);
    }

    /**
     * 用1024byte缓冲区的文件流进行文件拷贝
     *
     * @param sourceFile
     * @param targetFile
     * @throws IOException
     */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(targetFile);
        System.out.println("length: " + fis.available() + " Bytes");
        byte[] buffer = new byte[1024];
        int offset = 0;
        while ((offset = fis.read(buffer)) != -1) {
            System.out.println("offset: " + offset);
            fos.write(buffer, 0, offset);
        }
        fis.close();
        fos.close();
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
