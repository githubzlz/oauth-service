package com.zlz.oauth.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wb_zhulinzhong
 * @date 2022-04-01 19:45:31
 */
@Getter
@AllArgsConstructor
public enum OauthBizErrorEnum {

    NOT_FIND_LOGIN_TYPE(10001001, "未知的登录方式"),

    PWD_ERROR(10001002, "密码错误"),

    SAME_USERNAME_ERROR(10001003, "用户已存在"),

    NO_USER_ERROR(10001003, "用户不存在"),
    ;

    private final Integer code;

    private final String desc;
}
