package com.guai.system.service;

import com.guai.system.domain.SysUser;

/**
 * @author gqw
 * @date 2020-07-14
 */
public interface ISysUserService {
    SysUser selectUserByUserName(String userName);
    SysUser selectUserByUserId(Integer userId);
    String login(String username,String password,String code,String key);
}
