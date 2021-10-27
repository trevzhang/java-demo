package com.trevzhang.demo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author zhangchunguang.zcg
 * @since 2021/10/27 5:26 ����
 */
public class FileTest {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/zcg/Pictures/archieve/IMG_0668.MOV");
        byte[] bytes = getBytesFromFile(file);
        File outFile = new File("/Users/zcg/Pictures/" + UUID.randomUUID().toString() + ".MOV");
        FileOutputStream fos = new FileOutputStream(outFile);
        fos.write(bytes);
    }

    // ����һ��byte����
    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);// ��ȡ�ļ���С
        long lengths = file.length();
        System.out.println("lengths = " + lengths);
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
        return bytes;
    }
}
