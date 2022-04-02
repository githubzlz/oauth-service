package com.zlz.oauth.common.transfer;

import com.zlz.basic.enums.DeletedStatusEnum;
import com.zlz.basic.trace.TraceContext;
import com.zlz.oauth.common.dos.user.UserDO;
import com.zlz.oauth.common.dto.UserDTO;
import com.zlz.oauth.common.enums.BannedEnum;
import com.zlz.oauth.common.req.AccountCreatReq;

import java.util.Date;

/**
 * @author zhulinzhong
 * @date 2022-04-02 16:14:21
 */
public class UserTransfer {

    public static UserDO buildUserDO(AccountCreatReq req) {
        Date now = new Date();
        UserDO user = new UserDO();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setAvator(req.getAvator());
        user.setNickname(req.getNickname());
        user.setIsBaned(BannedEnum.NOT_BAN.getCode());
        user.setIsCancel(DeletedStatusEnum.NOT_DELETED.getCode());
        user.setCreator(TraceContext.getUserId());
        user.setCreatedTime(now);
        user.setOperator(TraceContext.getUserId());
        user.setModifiedTime(now);
        user.setLastLoginTime(now);
        return user;
    }


    public static UserDTO trans2UserDTO(UserDO user){
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getId());
        dto.setNickname(user.getNickname());
        dto.setLastLoginTime(user.getLastLoginTime());
        return dto;
    }
}
