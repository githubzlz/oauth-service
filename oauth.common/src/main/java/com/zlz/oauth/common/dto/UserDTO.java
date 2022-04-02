package com.zlz.oauth.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author zhulinzhong
 * @date 2022-04-01 19:31:51
 */
@Data
public class UserDTO {

    private Long userId;

    private Date lastLoginTime;

    private String nickname;

    private String token;

}
