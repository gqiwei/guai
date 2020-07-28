package com.guai.system.controller;

import com.guai.common.utils.R;
import com.guai.system.dto.LoginMessageDTO;
import com.guai.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录相关
 * @author gqw
 * @date 2020-07-17
 */
@RestController
public class LoginController {
    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("/login")
    public R login(@RequestBody LoginMessageDTO loginBody){

        return R.ok(sysUserService.login(loginBody.getUsername(),loginBody.getPassword()));
    }
}
