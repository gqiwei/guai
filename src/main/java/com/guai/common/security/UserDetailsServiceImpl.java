package com.guai.common.security;

import com.guai.common.exception.BaseException;
import com.guai.system.domain.SysUser;
import com.guai.system.service.ISysMenuService;
import com.guai.system.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author gqw
 * @date 2020-07-17
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据username查询账号
        SysUser user = sysUserService.selectUserByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户：" + username + " 不存在");
        }else if(0==user.getStatus()){
            throw new BaseException("用户已被禁用");
        }
        //根据userid查询权限标识
        List<String> perms = sysMenuService.selectPermsByUserId(user.getUserId());
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(permsSet.toArray(new String[0]));
        return new SecurityUser(user.getUserId(),user.getUserName(),user.getPassword(),authorities) ;
    }
}
