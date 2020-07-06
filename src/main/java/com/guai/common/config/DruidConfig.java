//package com.guai.common.config;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///**
// * @author gqw
// * @date 2020-07-03
// */
//@Configuration
//public class DruidConfig {
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.druid")
//    public DataSource dataSource(){
//        return DruidDataSourceBuilder.create().build();
//    }
//}
