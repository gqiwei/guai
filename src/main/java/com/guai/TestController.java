package com.guai;

import com.alibaba.fastjson.JSONObject;
import com.guai.common.utils.R;
import com.guai.system.dao.TestDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gqw
 * @date 2020-06-29
 */
@Api(value = "测试内容",tags="测试")
@RestController
public class TestController {
    @Autowired
    private TestDAO testDAO;

    @ApiOperation(value="测试接口",notes = "测试接口访问")
    @GetMapping("/test")
    public String test(){
        return "{code:200}";
    }

    @ApiOperation(value="登录",notes = "登录")
    @ApiImplicitParam(name = "body",value = "登录信息" ,required = true,paramType = "body")
    @PostMapping("user/login")
    public Map login(@RequestBody JSONObject body){
        System.out.println(body.toJSONString());
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("token","123123123");//token 暂时随便写写
        result.put("data",data);
        result.put("code",20000);
        return result;
    }

    @ApiOperation(value="获取用户信息",notes = "获取用户信息")
    @ApiImplicitParam(name = "token",value = "用户token" ,required = true,paramType = "query")
    @GetMapping("user/info")
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

    @PreAuthorize("hasAuthority('sys:example')")
    @ApiOperation(value="响应消息主体测试",notes = "测试接口访问")
    @GetMapping("/r")
    public R r(String some){
        return R.ok(some);
    }

    @ApiOperation(value="全局异常捕获",notes = "测试接口访问")
    @GetMapping("ex")
    public R r() throws Exception {
        throw new Exception("错误信息");
    }

    @ApiOperation(value="swagger接口测试",notes = "测试接口访问")
    @GetMapping("sw")
    public R<TestDO> swagger(){
        TestDO t = new TestDO();
        t.setAge(10);
        t.setName("张三");
        return R.ok(t);
    }

    @GetMapping("xss")
    public R xss(String url){
        return R.ok(url);
    }

    @GetMapping("mybatis")
    public R mybatis(){
        return R.ok(testDAO.select());
    }
}
