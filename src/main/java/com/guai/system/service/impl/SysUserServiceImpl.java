package com.guai.system.service.impl;

import com.guai.common.security.SecurityUser;
import com.guai.common.utils.JwtUtil;
import com.guai.system.dao.SysUserDao;
import com.guai.system.domain.SysUser;
import com.guai.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author gqw
 * @date 2020-07-14
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public SysUser selectUserByUserName(String userName) {
        return sysUserDao.selectUserByUserName(userName);
    }

    @Override
    public String login(String username, String password) {
        //会调用我们定义的 UserDetailsServiceImpl 的 #loadUserByUsername(String username)
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user);
        return token;
    }
}
