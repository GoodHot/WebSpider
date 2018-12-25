package com.goodHot.fun.domain;

import com.goodHot.fun.domain.media.AbstractMedia;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class Collect {

    @Id
    private String id;

    private String title;

    private String userId;

    /**
     * 内容
     */
    private List<AbstractMedia> medias;

    private Date created;

    private Date modified;

}
