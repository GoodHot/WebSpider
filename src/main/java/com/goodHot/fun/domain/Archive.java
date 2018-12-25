package com.goodHot.fun.domain;

import com.goodHot.fun.common.BaseDomain;
import com.goodHot.fun.domain.media.AbstractMedia;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 资源，爬虫抓取到的资源
 */
@Data
@ToString
public class Archive extends BaseDomain {

    /**
     * 来源，coub, 9gag, reddit, imgur
     */
    private String source;

    /**
     * 唯一编码，去重使用
     */
    private String unique;

    /**
     * 封面图片(url)
     */
    private String poster;

    /**
     * 标题
     */
    private String title;

    /**
     * 翻译后的标题
     */
    private String translateTitle;

    /**
     * 是否需要转码 true = 转码  false = 不转码
     */
    private Boolean transcoding;

    /**
     * 状态 com.goodHot.fun.enums.ArchiveEnum.Status
     */
    private Integer status;

    /**
     * 内容
     */
    private List<AbstractMedia> medias;

}
