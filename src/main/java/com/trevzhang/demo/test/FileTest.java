package com.trevzhang.demo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhangchunguang.zcg
 * @since 2021/10/27 5:26 ����
 */
public class FileTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("/Users/zcg/Pictures/archieve/1.sketch");
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = getBytesFromInputStream(fis);
        byte[] bytes1 = getBytesFromFile(file);
    }

    /**
     * ��inputStream��ȡ�ֽ�����
     *
     * @param input
     * @return
     * @throws IOException
     */
    public static byte[] getBytesFromInputStream(InputStream input) throws IOException {
        long start = System.currentTimeMillis();
        int length = input.available();
        if (length > Integer.MAX_VALUE) {
            // �ļ�̫���޷���ȡ
            throw new IOException("File is to large");
        }
        // ����һ�������������ļ�����
        byte[] bytes = new byte[(int) length];// ��ȡ���ݵ�byte������
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = input.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // ȷ���������ݾ�����ȡ
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file");
        }
        // Close the input stream and return bytes
        input.close();
        System.out.println("input length = " + length + ", read time = " + (System.currentTimeMillis() - start) + "ms");
        return bytes;
    }

    /**
     * ��file��ȡ�ļ�����
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] getBytesFromFile(File file) throws IOException {
        long start = System.currentTimeMillis();
        InputStream is = new FileInputStream(file);// ��ȡ�ļ���С
        long lengths = file.length();
        if (lengths > Integer.MAX_VALUE) {
            // �ļ�̫���޷���ȡ
            throw new IOException("File is to large " + file.getName());
        }
        // ����һ�������������ļ�����
        byte[] bytes = new byte[(int) lengths];// ��ȡ���ݵ�byte������
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // ȷ���������ݾ�����ȡ
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        // Close the input stream and return bytes
        is.close();
        System.out.println("file length = " + lengths + ", read time = " + (System.currentTimeMillis() - start) + "ms");
        return bytes;
    }
}
