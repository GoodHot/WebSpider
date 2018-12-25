package com.goodHot.fun.rest.admin;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(WebsiteConfig.ADMIN_PREFIX + "/upload")
@RestController
public class UploadRest {

    @PostMapping("/image")
    public RestResult handleFileUpload(@RequestParam("file") MultipartFile file) {
//        storageService.store(file);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
//        return "redirect:/";
        System.out.println(file.getOriginalFilename());
        // TODO: 2018/12/24 上传到OSS
        return RestResult.ok("https://tvax2.sinaimg.cn/crop.62.10.182.182.180/b992234fly8foofgubgaqj2094094dg7.jpg");
    }

}
