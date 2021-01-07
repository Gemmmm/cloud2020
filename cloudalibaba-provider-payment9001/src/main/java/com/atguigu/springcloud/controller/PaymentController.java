package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DELL
 * @date 2021-1-1 14:45
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;
    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable Integer id){
        return "nacos,serverPort:"+serverPort+",id:"+id;
    }

}
