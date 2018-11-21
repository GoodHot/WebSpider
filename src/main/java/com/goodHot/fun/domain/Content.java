package com.goodHot.fun.domain;

import com.goodHot.fun.domain.media.AbstractMedia;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class Content {

    @Id
    private String id;

    /**
     * 作者
     */
    private String authorId;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 浏览数
     */
    private Integer views = 0;

    /**
     * 喜欢数
     */
    private Integer like = 0;

    /**
     * 不喜欢数
     */
    private Integer dislike = 0;

    /**
     * 评论数量
     */
    private Integer comments = 0;

    /**
     * 来源
     */
    private String source;

    /**
     * 分类ID
     */
    @DBRef
    private Category category;

    /**
     * 简要内容
     */
    private List<AbstractMedia> medias;

}
