package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DELL
 * @date 2020-12-23 13:50
 */
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator myRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes=routeLocatorBuilder.routes();
        //routes.route("/path_route_atguigu2",r-> r.path("/guonei").uri("http://news.baidu.com/guonei"));
        return  routes.build();
    }
}
