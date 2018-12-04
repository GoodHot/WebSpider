package com.goodHot.fun.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 内容分类
 */
@Data
@ToString
@NoArgsConstructor
public class Category {

    @Id
    private String id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 英文名
     */
    private String eName;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否显示
     */
    private Boolean isShow;

    private Date created;

    private Date modified;

}
