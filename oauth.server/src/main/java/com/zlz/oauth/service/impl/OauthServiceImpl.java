package com.zlz.oauth.service.impl;

import com.zlz.basic.response.ResultSet;
import com.zlz.oauth.common.constants.OauthBasicConstants;
import com.zlz.oauth.common.dos.user.UserDO;
import com.zlz.oauth.common.dto.TokenDTO;
import com.zlz.oauth.common.dto.UserDTO;
import com.zlz.oauth.common.enums.LoginTypeEnum;
import com.zlz.oauth.common.enums.OauthBizErrorEnum;
import com.zlz.oauth.common.exception.OauthBizException;
import com.zlz.oauth.common.req.AuthCheckReq;
import com.zlz.oauth.common.transfer.UserTransfer;
import com.zlz.oauth.common.util.PassWordUtil;
import com.zlz.oauth.common.util.TokenUtil;
import com.zlz.oauth.service.OauthService;
import com.zlz.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhulinzhong
 * @date 2022-04-01 19:33:40
 */
@Service
public class OauthServiceImpl implements OauthService {

    @Autowired
    private UserService userService;

    @Override
    public ResultSet<String> authLogin(AuthCheckReq req) {
        LoginTypeEnum loginTypeEnum = LoginTypeEnum.getByType(req.getType());
        switch (loginTypeEnum) {
            case USERNAME_PASSWORD:
                return usernameAndPwd(req);
            case EMAIL_CHECK_CODE:
                return emailAndCheckCode(req);
            case THIRD_SYS_GITHUB:
                return usernameAndPwd(req);
            default:
                throw new OauthBizException(OauthBizErrorEnum.NOT_FIND_LOGIN_TYPE);
        }
    }

    @Override
    public ResultSet<String> authLogout(AuthCheckReq req) {
        return null;
    }

    @Override
    public ResultSet<UserDTO> authCheck(TokenDTO token) {
        UserDTO user = TokenUtil.verify(token.getToken());
        if(user != null){
            return ResultSet.success(user);
        }
        return ResultSet.error("token认证失败");
    }

    private ResultSet<String> usernameAndPwd(AuthCheckReq req) {
        UserDO user = userService.selectByUsername(req.getUsername());
        if(user == null){
            throw new OauthBizException(OauthBizErrorEnum.NO_USER_ERROR);
        }
        if (!PassWordUtil.verify(req.getPassword(), user.getSalt(), user.getPassword())) {
            throw new OauthBizException(OauthBizErrorEnum.PWD_ERROR);
        }
        String sign = TokenUtil.sign(UserTransfer.trans2UserDTO(user));
        return ResultSet.success(sign);
    }


    private ResultSet<String> emailAndCheckCode(AuthCheckReq req) {
        return ResultSet.success();
    }
}
