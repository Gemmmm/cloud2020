package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author DELL
 * @date 2020-11-23 21:08
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced //赋予resttemple负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
