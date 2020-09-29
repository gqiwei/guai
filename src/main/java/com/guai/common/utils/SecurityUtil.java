package com.guai.common.utils;

import com.guai.common.exception.BaseException;
import com.guai.common.security.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author gqw
 * @date 2020-08-18
 */
public class SecurityUtil {

    public static SecurityUser getUser(){
        try{
            return (SecurityUser) getAuthentication().getPrincipal();
        }catch (Exception e){
            throw new BaseException("获取用户异常");
        }
    }

    /**
     * 获取Authentication
     */
    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
