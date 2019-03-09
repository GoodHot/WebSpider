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
public class VedioUtil {
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
    public String waterMarkByFFpemg(String target, String waterMark, String outputDir) throws IOException, InterruptedException {
        if (!watermarkConfig.getActive()) {
            log.debug("视频 添加水印 【关闭】");
            return target;
        }
        ExceptionHelper.param(isWindows(), "请在Linux环境下，视频加水印");
        File targetFile = new File(target);
        ExceptionHelper.param(!targetFile.isFile(), "target是一个文件");
        ExceptionHelper.param(!new File(waterMark).isFile(), "waterMark是一个文件");
        ExceptionHelper.param(!new File(outputDir).isDirectory(), "输出是一个目录");

        outputDir = outputDir.endsWith("/") ? outputDir : outputDir + "/";
        // test.mp4
        String[] targets = targetFile.getName().split("\\.");
        // test_wm.mp4
        String outFilePath = outputDir + targets[0] + "_wm." + targets[1];
        File outFile = new File(outFilePath);
        if (outFile.exists()) {
            log.info("视频 添加水印，视频存在：{}", outFilePath);
            // 文件存在，返回
            return outFilePath;
        }

        //  ffmpeg -i likeS.mp4 -i cat.gif -filter_complex "overlay='if(lte(t,5), 0, main_w-overlay_w)':'if(lte(t,5), 0, main_h-overlay_h)'" likeS_vm.mp4
        int exitCode = ProcessCommandUtil.processCommand("ffmpeg", "-i", target,
                "-i", waterMark,
                "-filter_complex", "overlay='if(lte(t,5), 0, main_w-overlay_w)':'if(lte(t,5), 0, main_h-overlay_h)'",
                outFilePath);
        ExceptionHelper.param(exitCode != 0, "视频 添加水印 执行失败");
        return outFilePath;
    }
}
