package com.goodHot.fun.util;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.regex.Pattern;

/**
 * 文件及目录相关工具
 */
public class FileUtil {

    /**
     * 目标路径下(单一层级)，符合正则的所有文件列表
     *
     * @param dir   目标路径
     * @param regex 筛选文件名
     * @return
     */
    public File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            public boolean accept(File dir, String name) {
                // 如果没有后缀，直接过
                if (!name.contains(".")) {
                    return true;
                }
                return pattern.matcher(name).matches();
            }
        });
    }

    /**
     * 递归遍历目标路径，对文件名符合正则的文件，做解码处理
     *
     * @param dir           目标路径
     * @param regex         匹配文件名
     * @param decodeHandler 解码操作
     */
    public void recurseDirsAndHandleFile(File dir, final String regex, DecodeHandler decodeHandler) {
        for (File file : local(dir, regex)) {
            if (file.isFile()) {
                decodeHandler.decode(file);
            } else {
                recurseDirsAndHandleFile(file, regex, decodeHandler);
            }
        }
    }

    public void recurseDirsAndHandleFile(String path, final String regex, DecodeHandler decodeHandler) {
        recurseDirsAndHandleFile(new File(path), regex, decodeHandler);
    }

    /**
     * the traditional io way
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArrayIO(String filename) throws IOException {

        File f = new File(filename);
        if(!f.exists()){
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());
        BufferedInputStream in = null;
        try{
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while(-1 != (len = in.read(buffer,0,buf_size))){
                bos.write(buffer,0,len);
            }
            return bos.toByteArray();
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try{
                in.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }


    /**
     * NIO way
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArrayNIO(String filename)throws IOException{

        File f = new File(filename);
        if(!f.exists()){
            throw new FileNotFoundException(filename);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try{
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)channel.size());
            while((channel.read(byteBuffer)) > 0){
                // do nothing
//              System.out.println("reading");
            }
            return byteBuffer.array();
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try{
                channel.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            try{
                fs.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Mapped File  way
     * MappedByteBuffer 可以在处理大文件时，提升性能
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArrayBigFile(String filename)throws IOException{

        FileChannel fc = null;
        try{
            fc = new RandomAccessFile(filename,"r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).load();
            System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int)fc.size()];
            if (byteBuffer.remaining() > 0) {
//              System.out.println("remain");
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try{
                fc.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void main(String[] args) {
        File file = new File("/Users/yanwenyuan/aCode4F/coubMP4Decode/src/main/resources");
//        System.out.println(JSON.toJSONString(file.list()));
//        System.out.println(JSON.toJSONString(file.listFiles()));

        System.out.println(JSON.toJSONString(local(file, "^(?!NICE_)[\\s\\S]*.mp4$")));
    }
}
