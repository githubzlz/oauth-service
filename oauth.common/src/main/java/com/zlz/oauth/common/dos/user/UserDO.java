package com.zlz.oauth.common.dos.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * user
 *
 * @author zhulinzhong
 */
@Data
@TableName("user")
public class UserDO {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 头像图片路径
     */
    private String avator;

    /**
     * 0 正常 1 封禁
     */
    private Integer isBaned;

    /**
     * 状态,0:正常，1:已注销
     */
    private Integer isCancel;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 操作人
     */
    private Long operator;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 数据创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date modifiedTime;

}