package com.zlz.oauth.common.dto;

import lombok.Data;

/**
 * @author zhulinzhong
 * @date 2022-04-02 17:56:23
 */
@Data
public class TokenDTO {

    private String token;

    private String url;

    private Long userId;
}
