package com.guai.system.dao;

import com.guai.system.domain.SysUser;

/**
 * @author gqw
 * @date 2020-07-14
 */
public interface SysUserDao {
    SysUser selectUserByUserName(String userName);
}
