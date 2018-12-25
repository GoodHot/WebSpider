package com.goodHot.fun.domain;

import com.goodHot.fun.enums.DeviceTypeEnum;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * 评论
 */
@Data
@ToString
public class Comment {

    @Id
    private String id;

    private String postId;

    /**
     * 回复内容
     */
    private String content;

    private String userId;

    /**
     * 回复
     */
    private List<Comment> replys;

    /**
     * 设备类型
     */
    private DeviceTypeEnum deviceType;

    private Integer like;

    private Integer dislike;

    /**
     * 是否置顶
     */
    private Boolean isTop;

    /**
     * 评论人ID
     */
    private String ip;

    private Boolean deleted;

    private Date created;

    private Date modified;

}
