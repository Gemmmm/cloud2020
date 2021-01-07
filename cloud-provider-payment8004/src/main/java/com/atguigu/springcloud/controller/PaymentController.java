package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author DELL
 * @date 2020-12-19 15:04
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("payment/zk")
    public String paymentzk() {
        return "springcloud with zookeeper " + serverPort + " " + UUID.randomUUID().toString();
    }
}
