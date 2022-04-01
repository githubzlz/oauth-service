package com.zlz.oauth.service;

import com.zlz.oauth.common.dos.user.UserDO;

/**
 * @author zhulinzhong
 * @date 2022-04-01 19:36:55
 */
public interface UserService {

    UserDO selectByUsername(String username);
}
