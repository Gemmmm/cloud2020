package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author DELL
 * @date 2020-11-23 21:05
 */
@RestController
@Slf4j
public class OrderController {
    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://cloud-provider-payment";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo() {
        String result = restTemplate.getForObject(PAYMENT_URL + "/payment/consul", String.class);
        return result;
    }

}
