package com.goodHot.fun.domain;

import com.goodHot.fun.common.BaseDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Tag extends BaseDomain {

    private String name;

}
