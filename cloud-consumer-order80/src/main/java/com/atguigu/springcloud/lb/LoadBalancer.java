package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author DELL
 * @date 2020-12-20 20:33
 */
public interface LoadBalancer {

     ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
