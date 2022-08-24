package com.trevzhang.demo.test;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import org.junit.Test;

import java.io.File;

public class FileTypeTest {
    @Test
    public void getFileTypeTest() {
        // 即使修改了文件后缀名，也能正确识别文件格式
        File file1 = new File("/Users/zcg/Pictures/临时图片/卡特琳娜岛2.png");
        File file2 = new File("/Users/zcg/Pictures/临时图片/上海飞汕头自由行.mp4");
        File file3 = new File("/Users/zcg/Pictures/临时图片/上海汕头自由行.jpg");

        printFileType(file1);
        printFileType(file2);
        printFileType(file3);
    }

    private void printFileType(File file) {
        String fileType = FileTypeUtil.getType(file);
        System.out.printf("%s file type: %s\n", file.getName(), fileType);
    }
}
