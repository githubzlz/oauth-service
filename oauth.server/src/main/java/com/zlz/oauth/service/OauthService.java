package com.zlz.oauth.service;

import com.zlz.basic.response.ResultSet;
import com.zlz.oauth.common.dto.TokenDTO;
import com.zlz.oauth.common.dto.UserDTO;
import com.zlz.oauth.common.req.AuthCheckReq;

/**
 * @author zhulinzhong
 * @date 2022-04-01 19:33:26
 */
public interface OauthService {

    /**
     * 用户登录
     * @param req
     * @return
     */
    ResultSet<String> authLogin(AuthCheckReq req);

    /**
     * 退出登录
     * @param req
     * @return
     */
    ResultSet<String> authLogout(AuthCheckReq req);

    /**
     * token认证
     * @param token
     * @return
     */
    ResultSet<UserDTO> authCheck(TokenDTO token);
}
