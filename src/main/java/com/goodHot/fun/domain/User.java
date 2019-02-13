package com.goodHot.fun.domain;

import com.goodHot.fun.common.BaseDomain;
import com.goodHot.fun.enums.SocialEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User extends BaseDomain {

    public static final boolean STAFF_YES = true;
    public static final boolean STAFF_NO = true;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 内部账号 true = 内部账号
     */
    private Boolean staff = false;

    /**
     * 账号来源渠道
     */
    private SocialEnum.Channel channel;


}
