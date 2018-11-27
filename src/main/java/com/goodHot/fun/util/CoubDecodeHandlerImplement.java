package com.goodHot.fun.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class CoubDecodeHandlerImplement implements DecodeHandler {

    public void decode(File file) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");) {
            randomAccessFile.seek(0);
            randomAccessFile.write(new byte[]{0x00, 0x00}, 0, 2);
            file.renameTo(new File(file.getParent() + "/NICE_" + file.getName()));
        } catch (IOException e) {
            // TODO: 2018/11/23 log
        }
    }

    public static void main(String[] args) {
        // 处理『不以 NICE_ 开头的，以.mp4结尾的所有文件』
        new FileUtil().recurseDirsAndHandleFile("/Users/yanwenyuan/aCode4F/coubMP4Decode/src/main/resources/",
                "^(?!NICE_)[\\s\\S]*.mp4$",
                new CoubDecodeHandlerImplement());
    }
}
