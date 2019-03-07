package com.goodHot.fun.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@Slf4j
public class Download {

    public static final int BUFFER_SIZE = 1024;
    public static final int EMPTY_BUFFER_FLAG = -1;

    public void downloadFromUrl(String fileUrl, String filePath) throws IOException {
        URL url = new URL(fileUrl);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        try (InputStream bufferIn = new BufferedInputStream(conn.getInputStream());
             ByteArrayOutputStream bufferOut = new ByteArrayOutputStream();
             FileOutputStream fileOutputStream = new FileOutputStream(filePath);) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bufferCurrentSize = 0;
            while (EMPTY_BUFFER_FLAG != (bufferCurrentSize = bufferIn.read(buffer))) {
                bufferOut.write(buffer, 0, bufferCurrentSize);
            }
            bufferIn.close();
            bufferOut.close();
            byte[] response = bufferOut.toByteArray();

            fileOutputStream.write(response);

        } catch (IOException e) {
            log.error("[DownloadFile]down load fail -> url:{}, localPath:{}, error: {}", url, filePath, e.getMessage());
            throw e;
        }
    }

    public static void main(String[] args) throws IOException {
        String url = "https://coubsecure-s.akamaihd.net/get/b100/p/coub/simple/cw_file/ab49b1c9d79/f786820f073ab03822511/muted_mp4_med_size_1542278618_muted_med.mp4";
        String filePath = "/Users/yanwenyuan/aCode4F/coubMP4Decode/src/main/resources/muted_mp4_med_size_1542278618_muted_med.mp4";
        new Download().downloadFromUrl(url, filePath);
    }

}
