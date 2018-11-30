package com.goodHot.fun.rest.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.util.Encrypts;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("fanyi")
@RestController
public class FanyiRest {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/f")
    @CrossOrigin(WebsiteConfig.ADMIN_WEBSITE)
    public RestResult f() {
        String q = "Tony was able to create the Mark I in a cave with a box of scraps - what will he be capable of in the ruins of an advanced civilization?";
        String appid = "20181128000240516";
        String salt = RandomUtils.nextInt(100000, 999999) + "";
        String md5 = Encrypts.md5(appid + q +salt + "n7rDONXmDEK02bAmeJDI");
        String url = String.format("http://api.fanyi.baidu.com/api/trans/vip/translate?q=%s&from=auto&to=zh&appid=%s&salt=%s&sign=%s", q, appid, salt, md5);
        ResponseEntity<String> out = restTemplate.getForEntity(url, String.class);
        JSONObject json = JSON.parseObject(out.getBody());
        String rst = json.getJSONArray("trans_result").getJSONObject(0).getString("dst");
        return RestResult.ok(rst);
    }

    public static void main(String[] args) {
        FanyiRest rest = new FanyiRest();
        rest.restTemplate = new RestTemplate();
        System.out.println(rest.f());
    }

}
