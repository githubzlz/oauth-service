package com.zlz.route.common.trace;

import com.zlz.route.common.user.User;

/**
 * @author zhulinzhong
 * @date 2022-03-07 20:07:40
 */
public class TraceContext {

    private TraceContext() {
    }

    public static final ThreadLocal<Trace> TRACE = new ThreadLocal<>();

    /**
     * 请求开始获取并保存用户信息
     * @param trace
     */
    public static void init(Trace trace) {
        TRACE.set(trace);
    }

    /**
     * TraceId
     * @return
     */
    public static Long getTraceId() {
        Trace trace = TRACE.get();
        if (null == trace) {
            return null;
        }
        return trace.getTraceId();
    }

    /**
     * 用户信息
     * @return
     */
    public static User getUserInfo() {
        Trace trace = TRACE.get();
        if (null == trace) {
            return null;
        }
        return trace.getUser();
    }

    /**
     * 用户id
     * @return
     */
    public static Long getUserId() {
        Trace trace = TRACE.get();
        if (null == trace) {
            return null;
        }
        User user = trace.getUser();
        if (null == user) {
            return null;
        }
        return user.getId();
    }

    /**
     * 请求完成销毁用户信息
     */
    public static void destroy() {
        TRACE.remove();
    }

}
