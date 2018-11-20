package com.guorer.webspider.domain;

import com.guorer.webspider.domain.media.AbstractMedia;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class Store {

    /**
     * 来源
     */
    private String source;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 封面图片
     */
    private String poster;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private List<AbstractMedia> medias;

}
