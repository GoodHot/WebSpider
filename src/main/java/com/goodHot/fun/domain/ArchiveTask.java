package com.goodHot.fun.domain;

import com.goodHot.fun.common.BaseDomain;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ArchiveTask extends BaseDomain {

    private String categoryId;

    private Archive archive;

    /**
     * 是否处理中：true == 处理中
     */
    private Boolean processing;

    /**
     * 重试次数
     */
    private Integer retry = 0;

}
