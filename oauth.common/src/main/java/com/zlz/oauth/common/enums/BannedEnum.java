package com.zlz.oauth.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhulinzhong
 * @date 2022-04-02 16:16:11
 */
@Getter
@AllArgsConstructor
public enum BannedEnum {

    NOT_BAN(0, "正常"),

    BAN(1, "封禁"),
    ;

    private final Integer code;

    private final String desc;
}
