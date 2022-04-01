package com.zlz.oauth.controller;

import com.zlz.basic.response.ResultSet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhulinzhong
 * @date 2022-04-01 19:27:14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/create/account")
    public ResultSet createAccount() {
        return ResultSet.success();
    }
}
