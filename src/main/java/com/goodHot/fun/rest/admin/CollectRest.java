package com.goodHot.fun.rest.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.domain.Collect;
import com.goodHot.fun.domain.media.AbstractMedia;
import com.goodHot.fun.domain.media.JPEGMedia;
import com.goodHot.fun.domain.media.MP4Media;
import com.goodHot.fun.domain.media.TextMedia;
import com.goodHot.fun.enums.MediaEnum;
import com.goodHot.fun.service.CollectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping(WebsiteConfig.ADMIN_PREFIX + "/collect")
@RestController
@CrossOrigin(origins = WebsiteConfig.ADMIN_WEBSITE)
public class CollectRest {

    @Autowired
    private CollectService collectService;

    /**
     * 合集 新增
     *
     * @param body
     * @return
     */
    @PostMapping
    public RestResult add(@RequestBody String body) {
        Collect collect = new Collect();
        // TODO: 2018/12/27 保存当前用户信息 collect.setUserId
        JSONObject json = JSON.parseObject(body);
        collect.setTitle(json.getString("title"));
        collect.setCover(convertMedia(json.getJSONObject("cover")));
        JSONArray mediasJson = json.getJSONArray("medias");
        List<AbstractMedia> medias = new ArrayList<>(mediasJson.size());
        for (int i = 0; i < mediasJson.size(); i++) {
            JSONObject obj = mediasJson.getJSONObject(i);
            medias.add(convertMedia(obj));
        }
        collect.setMedias(medias);
        return RestResult.ok(collectService.insert(collect));
    }

    /**
     * 合集 列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{index}_{size}")
    public RestResult list(@PathVariable("index") Integer pageIndex, @PathVariable("size") Integer pageSize) {
        Page<Collect> page = collectService.page(pageIndex, pageSize);
        return RestResult.ok(page);
    }

    private AbstractMedia convertMedia(JSONObject json) {
        String type = json.getString("type");
        if (type.equals(MediaEnum.VIDEO.name())) {
            return json.toJavaObject(MP4Media.class);
        } else if (type.equals(MediaEnum.TEXT.name())) {
            return json.toJavaObject(TextMedia.class);
        } else if (type.equals(MediaEnum.JPEG.name())) {
            return json.toJavaObject(JPEGMedia.class);
        }
        return null;
    }

}
