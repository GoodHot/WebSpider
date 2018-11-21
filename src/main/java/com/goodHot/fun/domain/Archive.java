package com.goodHot.fun.domain;

import com.goodHot.fun.domain.media.AbstractMedia;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class Archive {

    /**
     * 来源，coub, 9gag, reddit, imgur
     */
    private String source;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 封面图片(url)
     */
    private String poster;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否需要转码 true = 转码  false = 不转码
     */
    private Boolean transcoding;

    /**
     * 内容
     */
    private List<AbstractMedia> medias;

}
