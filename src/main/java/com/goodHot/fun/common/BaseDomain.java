package com.goodHot.fun.common;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@ToString
public class BaseDomain {

    @Id
    private String id;

    private Date created;

    private Date modified;

    private Boolean deleted;

}
