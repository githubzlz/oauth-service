package com.zlz.oauth.common.exception;

import com.zlz.basic.exception.BizException;
import com.zlz.oauth.common.enums.OauthBizErrorEnum;

/**
 * @author zhulinzhong
 * @date 2022-04-01 19:44:53
 */
public class OauthBizException extends BizException {

    public OauthBizException(OauthBizErrorEnum errorEnum, Throwable cause) {
        super(errorEnum.getCode() + ":" + errorEnum.getDesc(), cause);
    }

    public OauthBizException(OauthBizErrorEnum errorEnum) {
        super(errorEnum.getCode() + ":" + errorEnum.getDesc());
    }
}
