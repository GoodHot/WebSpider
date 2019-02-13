package com.goodHot.fun.dto.req;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ArchivePassReq {

    private String id;

    private String category;

    private String title;

    private String userId;

}
