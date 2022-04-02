package com.zlz.oauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlz.oauth.common.dos.user.UserDO;
import com.zlz.oauth.common.param.UserParam;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhulinzhong
 * @date 2022-04-01 20:07:13
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

    /**
     * 创建用户
     * @param user
     * @return
     */
    int insertUser(UserDO user);

    /**
     * 根据用户id更新用户信息
     * @param user
     * @return
     */
    int updateUserById(UserDO user);

    /**
     * 查询用户信息
     * @param param
     * @return
     */
    UserDO selectByParam(UserParam param);
}
