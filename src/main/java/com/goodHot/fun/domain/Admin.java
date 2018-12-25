package com.goodHot.fun.domain;

import com.goodHot.fun.common.BaseDomain;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Admin extends BaseDomain {

    private String nickname;

    private String username;

    private String password;

    private String avatar;

    private Date lastLoginTime;

    private String lastLoginIP;

}
