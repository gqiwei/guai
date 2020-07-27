package com.guai.system.dao;

import java.util.List;

/**
 * @author gqw
 * @date 2020-07-14
 */
public interface SysMenuDao {
    List<String> selectPermsByUserId(Integer userId);
}
