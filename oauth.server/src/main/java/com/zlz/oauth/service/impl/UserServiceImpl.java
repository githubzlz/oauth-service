package com.zlz.oauth.service.impl;

import com.zlz.basic.response.ResultSet;
import com.zlz.oauth.common.dos.user.UserDO;
import com.zlz.oauth.common.enums.OauthBizErrorEnum;
import com.zlz.oauth.common.exception.OauthBizException;
import com.zlz.oauth.common.param.UserParam;
import com.zlz.oauth.common.req.AccountCreatReq;
import com.zlz.oauth.common.transfer.UserTransfer;
import com.zlz.oauth.common.util.PassWordUtil;
import com.zlz.oauth.common.util.TokenUtil;
import com.zlz.oauth.mapper.UserMapper;
import com.zlz.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhulinzhong
 * @date 2022-04-01 19:37:11
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDO selectByUsername(String username) {
        UserParam param = new UserParam();
        param.setUsername(username);
        return userMapper.selectByParam(param);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultSet<String> createAccount(AccountCreatReq req) {

        // 同名检查
        UserParam param = new UserParam();
        param.setUsername(req.getUsername());
        UserDO sameName = userMapper.selectByParam(param);
        if(sameName != null){
            throw new OauthBizException(OauthBizErrorEnum.SAME_USERNAME_ERROR);
        }

        // 保存信息
        UserDO userDO = UserTransfer.buildUserDO(req);
        String salt = PassWordUtil.salt();
        String pwd = PassWordUtil.pwd(req.getPassword(), salt);
        userDO.setSalt(salt);
        userDO.setPassword(pwd);
        userMapper.insert(userDO);

        // 登录
        String sign = TokenUtil.sign(UserTransfer.trans2UserDTO(userDO));
        ResultSet<String> success = ResultSet.success();
        success.setEntity(sign);
        return success;
    }
}
