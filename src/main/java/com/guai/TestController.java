package com.guai;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gqw
 * @date 2020-06-29
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "{code:200}";
    }

    @RequestMapping("user/login")
    public Map login(@RequestBody JSONObject body){
        System.out.println(body.toJSONString());
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("token","123123123");//token 暂时随便写写
        result.put("data",data);
        result.put("code",20000);
        return result;
    }

    @RequestMapping("user/info")
    public Map info(String token){
        System.out.println(token);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("roles","admin");
        data.put("introduction","I am a super administrator");
        data.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.put("name","Super Admin");
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("data",data);
        result.put("code",20000);
        return result;
    }
}
