package com.goodHot.fun.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Arrays;

@Slf4j
public class ProcessCommandUtil {

    /**
     * 执行命令
     * @param command 带占位符%s的命令
     */
    public static int processCommand(String... command) throws IOException, InterruptedException {
        log.info("执行命令：" + Arrays.stream(command).map(i -> i += " ").reduce("", String::concat));
        ProcessBuilder processBuilder = new ProcessBuilder();
        // 设置Process builder的工作路径 为 资源路径
        try {
            processBuilder.directory(new File(ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        processBuilder.command(command);
        Process process = processBuilder.start();
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String s = "";
        while ((s = stdInput.readLine()) != null) {
            log.info(s);
        }
        while ((s = stdError.readLine()) != null) {
            log.error(s);
        }
        int exitCode = process.waitFor();
        return exitCode;
    }
}
