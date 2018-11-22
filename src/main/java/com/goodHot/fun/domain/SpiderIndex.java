package com.goodHot.fun.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class SpiderIndex {

    @Id
    private String id;

    /**
     * 爬虫名称
     */
    private String name;

    /**
     * 索引
     */
    private byte[] patriciaTrie;

}
