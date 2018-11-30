package com.goodHot.fun.domain;

import com.goodHot.fun.domain.media.AbstractMedia;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class Archive {

    @Id
    private String id;

    /**
     * 来源，coub, 9gag, reddit, imgur
     */
    private String source;

    /**
     * 唯一编码，去重使用
     */
    private String unique;

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
