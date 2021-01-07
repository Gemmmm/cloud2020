package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author DELL
 * @date 2020-12-19 15:00
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient // 该注解用户向使用consul或者zookeeper作为注册中心注册服务
public class payment8004 {
    public static void main(String[] args) {
        SpringApplication.run(payment8004.class,args);
    }
}
