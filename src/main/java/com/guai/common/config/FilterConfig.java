package com.guai.common.config;

import com.guai.common.filter.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gqw
 * @date 2020-07-02
 * @desc 过滤器配置
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setOrder(1);//优先级 越小代表优先级越高
        filterRegistrationBean.setEnabled(true);//是否开启
        filterRegistrationBean.addUrlPatterns("/*");

        Map<String,String> initParameters = new HashMap<String,String>();
        initParameters.put("excludes","");//白名单，  多个以逗号相隔
        filterRegistrationBean.setInitParameters(initParameters);

        return filterRegistrationBean;

    }
}
