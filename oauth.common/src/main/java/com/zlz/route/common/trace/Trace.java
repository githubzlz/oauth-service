package com.zlz.route.common.trace;

import com.zlz.route.common.user.User;
import lombok.Data;

/**
 * @author zhulinzhong
 * @date 2022-03-08 10:57:57
 */
@Data
public class Trace {

    private Long traceId;

    private User user;
}
