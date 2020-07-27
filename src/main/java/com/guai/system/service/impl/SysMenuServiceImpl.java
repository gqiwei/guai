package com.guai.system.service.impl;

import com.guai.system.dao.SysMenuDao;
import com.guai.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gqw
 * @date 2020-07-14
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {
    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    public List<String> selectPermsByUserId(Integer userId) {
        return sysMenuDao.selectPermsByUserId(userId);
    }
}
