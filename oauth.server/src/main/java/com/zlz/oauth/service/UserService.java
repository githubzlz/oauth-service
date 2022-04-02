package com.zlz.oauth.service;

import com.zlz.basic.response.ResultSet;
import com.zlz.oauth.common.dos.user.UserDO;
import com.zlz.oauth.common.req.AccountCreatReq;

/**
 * @author zhulinzhong
 * @date 2022-04-01 19:36:55
 */
public interface UserService {

    UserDO selectByUsername(String username);

    /**
     * 创建新用户
     * @param req
     * @return
     */
    ResultSet<String> createAccount(AccountCreatReq req);
}
