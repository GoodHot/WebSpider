package com.goodHot.fun.domain;

import com.goodHot.fun.common.BaseDomain;
import com.goodHot.fun.domain.media.AbstractMedia;
import com.goodHot.fun.domain.media.MP4Media;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Collect extends BaseDomain {

    private String title;

    private String userId;

    private MP4Media cover;

    /**
     * 内容
     */
    private List<AbstractMedia> medias;

}
