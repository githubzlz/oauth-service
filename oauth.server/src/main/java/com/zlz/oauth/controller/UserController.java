package com.zlz.oauth.controller;

import com.zlz.basic.response.ResultSet;
import com.zlz.oauth.common.req.AccountCreatReq;
import com.zlz.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhulinzhong
 * @date 2022-04-01 19:27:14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create/account")
    public ResultSet<String> createAccount(@RequestBody AccountCreatReq req) {
        return userService.createAccount(req);
    }
}
