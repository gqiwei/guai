package com.guai.system.service.impl;

import com.guai.common.exception.BaseException;
import com.guai.common.security.SecurityUser;
import com.guai.common.utils.JwtUtil;
import com.guai.system.dao.SysUserDao;
import com.guai.system.domain.SysUser;
import com.guai.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    @Override
    public SysUser selectUserByUserName(String userName) {
        return sysUserDao.selectUserByUserName(userName);
    }

    @Override
    public SysUser selectUserByUserId(Integer userId) {
        return sysUserDao.selectUserByUserId(userId);
    }

    @Override
    public String login(String username, String password,String code,String key) {
        //会调用我们定义的 UserDetailsServiceImpl 的 #loadUserByUsername(String username)
        String captcha =(String) redisTemplate.opsForValue().get(key);
        if(code == null ||code.isEmpty()){
            throw new BaseException("验证码为空");
        }
        if(captcha == null){
            throw new BaseException("验证码过期");
        }
        if(!code.equals(captcha)){
            throw new BaseException("验证码错误");
        }
        redisTemplate.delete(key); //删除reids

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user);
        return token;
    }
}
