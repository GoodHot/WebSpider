package com.goodHot.fun.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class ArchiveTask {

    @Id
    private String id;

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
