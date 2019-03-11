package com.goodHot.fun.util;

import com.goodHot.fun.conf.WatermarkConfig;
import com.goodHot.fun.exception.ExceptionHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


@Slf4j
@Component
public class PictureUtil {
    public static final String SYS_PLATFORM = "os.name";
    public static final String SYS_PLATFORM_WINDOWS = "Windows";
    public static final String SYS_PLATFORM_MAC = "Mac OS X";

    @Autowired
    private WatermarkConfig watermarkConfig;

    /**
     * 是否是windows操作系统
     *
     * @return
     */
    public Boolean isWindows() {
        return SYS_PLATFORM_WINDOWS.equals(System.getProperty(SYS_PLATFORM));
    }

    /**
     * 加水印
     *
     * @param target    添加水印源文件
     * @param waterMark 水印文件（静态图、动态图、视频）
     * @param outputDir 添加水印后，生成文件
     * @return 添加水印后，生成文件地址
     */
    public String waterMarkByImageMagic(String target, String waterMark, String outputDir) throws IOException, InterruptedException {
        if (!watermarkConfig.getActive()) {
            log.debug("图片 添加水印 【关闭】");
            return target;
        }
        ExceptionHelper.param(isWindows(), "请在Linux环境下，图片加水印");
        File targetFile = new File(target);
        ExceptionHelper.param(!targetFile.isFile(), "target是一个文件");
        ExceptionHelper.param(!new File(waterMark).isFile(), "waterMark是一个文件");
        ExceptionHelper.param(!new File(outputDir).isDirectory(), "输出是一个目录");

        // convert target.jpg -compose over waterMark.png -geometry 50x50+0+0 -composite output.png
        outputDir = outputDir.endsWith("/") ? outputDir : outputDir + "/";
        String[] targets = targetFile.getName().split("\\.");
        String outFilePath = outputDir + targets[0] + "_wm." + targets[1];
        File outFile = new File(outFilePath);
        if (outFile.exists()) {
            // 文件存在，返回
            log.info("图片 添加水印，文件存在：{}", outFilePath);
            return outFilePath;
        }
        int exitCode = ProcessCommandUtil.processCommand("convert", target,
                "-compose", "over", waterMark,
                "-geometry", "50x50+0+0", "-composite", outFilePath);
        ExceptionHelper.param(exitCode != 0, "图片添加水印 执行失败");
        return outFilePath;
    }
}
