package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author DELL
 * @date 2020-11-23 21:05
 */
@RestController
@Slf4j
public class OrderController {
    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        log.info("消费者：" + PAYMENT_URL + "/consumer/payment/create");
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable Long id) {
        log.info("消费者：" + PAYMENT_URL + "/consumer/payment/get/" + id);
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("consumer/payment/get/entity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable Long id) {
        log.info("消费者：" + PAYMENT_URL + "/consumer/payment/get/" + id);
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return null;
        }
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

    @GetMapping("consumer/payment/zipkin")
    public String paymentZipKin(){

        String result = restTemplate.getForObject(PAYMENT_URL + "/payment/zipkin", String.class);
        return result;
    }
}
