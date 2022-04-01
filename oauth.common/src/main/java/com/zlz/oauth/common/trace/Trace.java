package com.zlz.oauth.common.trace;

import com.zlz.oauth.common.dos.user.UserDO;
import lombok.Data;

/**
 * @author zhulinzhong
 * @date 2022-03-08 10:57:57
 */
@Data
public class Trace {

    private Long traceId;

    private UserDO user;
}
