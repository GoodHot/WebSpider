package com.goodHot.fun.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SpiderReq {

    /**
     * 抓取数量
     */
    private Integer size;

}
