package com.zlz.oauth.common.req;

import lombok.Data;

/**
 * @author zhulinzhong
 * @date 2022-04-01 19:34:38
 */
@Data
public class AccountCreatReq {

    private Integer type;

    private String username;

    private String password;

    private String email;

    private String checkCode;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像图片路径
     */
    private String avator;

}
