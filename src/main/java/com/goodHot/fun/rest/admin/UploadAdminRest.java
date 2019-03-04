package com.goodHot.fun.rest.admin;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.service.UploadService;
import com.goodHot.fun.util.upyun.com.upyun.UpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping(WebsiteConfig.ADMIN_PREFIX + "/upload")
@RestController
public class UploadAdminRest {

    @Autowired
    private UploadService uploadService;

    /**
     * 用户上传头像
     *
     * @param file
     * @return
     */
    @PostMapping("/avatar")
    public RestResult avatar(@RequestParam("file") MultipartFile file) {
//        storageService.store(file);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
//        return "redirect:/";
        System.out.println(file.getOriginalFilename());
        // TODO: 2018/12/29 【暂不实现】上传到OSS
        return RestResult.ok("https://tvax2.sinaimg.cn/crop.62.10.182.182.180/b992234fly8foofgubgaqj2094094dg7.jpg");
    }

    /**
     * 用户上传 图片
     *
     * @param file
     * @return
     */
    @PostMapping("/image")
    public RestResult image(@RequestParam("file") MultipartFile file) throws IOException, UpException {
        String path = uploadService.uploadImage(file.getBytes(), file.getOriginalFilename());
        return RestResult.ok(path);
    }

    /**
     * 用户上传 视频
     *
     * @param file
     * @return
     */
    @PostMapping("/video")
    public RestResult video(@RequestParam("file") MultipartFile file) {
//        storageService.store(file);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
//        return "redirect:/";
        System.out.println(file.getOriginalFilename());
        // TODO: 2018/12/29 【暂不实现】上传到OSS
        return RestResult.ok("https://img-9gag-fun.9cache.com/photo/a8333K3_460svvp9.mp4");
    }

}
