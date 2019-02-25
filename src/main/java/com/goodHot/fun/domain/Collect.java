package com.goodHot.fun.domain;

import com.goodHot.fun.common.BaseDomain;
import com.goodHot.fun.domain.media.AbstractMedia;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Collect extends BaseDomain {

    private String title;

    private String userId;

    private AbstractMedia cover;

    /**
     * 内容
     */
    private List<AbstractMedia> medias;

    private Integer like;

    private Integer dislike;

    private Integer views;

    private Integer comments;

}
