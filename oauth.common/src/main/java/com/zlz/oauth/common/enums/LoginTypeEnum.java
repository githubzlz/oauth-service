package com.zlz.oauth.common.enums;

import com.zlz.oauth.common.exception.OauthBizException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wb_zhulinzhong
 * @date 2022-04-01 19:39:29
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {

    USERNAME_PASSWORD(0, "用户名密码登录"),

    EMAIL_CHECK_CODE(1, "邮箱验证码登录"),

    THIRD_SYS_GITHUB(2, "第三方登录-Github登录"),
    ;

    private final Integer type;

    private final String desc;

    public static LoginTypeEnum getByType(Integer type) {
        for (LoginTypeEnum typeEnum : LoginTypeEnum.values()) {
            if (typeEnum.getType().equals(type)) {
                return typeEnum;
            }
        }
        throw new OauthBizException(OauthBizErrorEnum.NOT_FIND_LOGIN_TYPE);
    }
}
