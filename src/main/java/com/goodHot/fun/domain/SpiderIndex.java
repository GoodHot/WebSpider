package com.goodHot.fun.domain;

import com.goodHot.fun.common.BaseDomain;
import lombok.Data;
import lombok.ToString;

/**
 * 爬虫索引
 */
@Data
@ToString
public class SpiderIndex extends BaseDomain {

    /**
     * 爬虫名称
     */
    private String name;

    /**
     * 索引
     */
    private byte[] patriciaTrie;

}
