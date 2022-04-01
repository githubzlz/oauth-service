package com.zlz.oauth.common.req;

import lombok.Data;

/**
 * @author zhulinzhong
 * @date 2022-04-01 19:34:38
 */
@Data
public class AuthCheckReq {

    private Integer type;

    private String username;

    private String password;

    private String email;

    private String checkCode;

}
