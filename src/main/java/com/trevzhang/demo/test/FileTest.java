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
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = getBytesFromInputStream(fis);

        long start = System.currentTimeMillis();
        File outFile = new File("/Users/zcg/Pictures/" + UUID.randomUUID() + ".MOV");
        FileOutputStream fos = new FileOutputStream(outFile);
        fos.write(bytes);
        fos.close();

        System.out.println("write time = " + (System.currentTimeMillis() - start) + "ms");
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
        byte[] bytes = new byte[input.available()];
        int size = input.read(bytes);
        input.close();
        System.out.println("length = " + size + ", read time = " + (System.currentTimeMillis() - start) + "ms");
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
        InputStream is = new FileInputStream(file);// 获取文件大小
        long lengths = file.length();
        System.out.println("length = " + lengths);
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
