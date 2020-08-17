package com.guai.system.controller;

import com.guai.common.utils.IdUtil;
import com.guai.common.utils.R;
import com.guai.system.dto.LoginMessageDTO;
import com.guai.system.service.ISysUserService;
import com.guai.system.vo.CodeVO;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 登录相关
 * @author gqw
 * @date 2020-07-17
 */
@RestController
public class LoginController {
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @PostMapping("/login")
    public R login(@RequestBody LoginMessageDTO loginBody){

        return R.ok(sysUserService.login(loginBody.getUsername(),loginBody.getPassword(),loginBody.getCode(),loginBody.getKey()));
    }

    @GetMapping("/loginCode")
    public R loginCode(){
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(150, 46);
        captcha.setLen(2);  // 几位数运算，默认是两位
        String result = captcha.text();  // 获取运算的结果：

        String key = IdUtil.getUNID();
        redisTemplate.opsForValue().set( key, result, 5, TimeUnit.MINUTES); //储存5分钟
        CodeVO codeVO = new CodeVO();
        codeVO.setKey(key);
        codeVO.setSrc(captcha.toBase64());

        return R.ok(codeVO);
    }
}
