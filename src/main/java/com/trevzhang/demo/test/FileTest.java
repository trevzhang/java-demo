package com.trevzhang.demo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author zhangchunguang.zcg
 * @since 2021/10/27 5:26 下午
 */
public class FileTest {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/zcg/Pictures/archieve/IMG_0668.MOV");
        byte[] bytes = getBytesFromFile(file);
        File outFile = new File("/Users/zcg/Pictures/" + UUID.randomUUID().toString() + ".MOV");
        FileOutputStream fos = new FileOutputStream(outFile);
        fos.write(bytes);
    }

    // 返回一个byte数组
    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);// 获取文件大小
        long lengths = file.length();
        System.out.println("lengths = " + lengths);
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
        return bytes;
    }
}
