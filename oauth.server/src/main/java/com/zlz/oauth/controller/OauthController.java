package com.zlz.oauth.controller;

import com.zlz.basic.response.ResultSet;
import com.zlz.oauth.common.dto.TokenDTO;
import com.zlz.oauth.common.dto.UserDTO;
import com.zlz.oauth.common.req.AuthCheckReq;
import com.zlz.oauth.service.OauthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhulinzhong
 * @date 2022-04-01 19:27:03
 */
@RestController
@RequestMapping("/auth")
public class OauthController {

    private final OauthService oauthService;

    public OauthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    @PostMapping("/login")
    public ResultSet<String> authLogin(@RequestBody AuthCheckReq req) {
        return oauthService.authLogin(req);
    }

    @PostMapping("/logout")
    public ResultSet<String> authLogout(@RequestBody AuthCheckReq req) {
        return oauthService.authLogout(req);
    }

    @PostMapping("/check")
    public ResultSet<UserDTO> authCheck(@RequestBody TokenDTO token) {
        return oauthService.authCheck(token);
    }
}
