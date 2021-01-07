package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymenService;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author DELL
 * @date 2020-12-22 13:10
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    public PaymenService paymenService;

    @Value("${server.port}")
    private  String serverPort;
    @GetMapping("payment/hystrix/get/{id}")
    public  String paymentOk(@PathVariable Integer id){
        String result = paymenService.PaymetInfo(id);
        log.info("result:"+result);
        return result;
    }
    @GetMapping("payment/hystrix/timeout/{id}")
    public  String paymentTimeOut(@PathVariable Integer id){
        String result = paymenService.PaymetInfo2(id);
        log.info("result:"+result);
        return result;
    }

    /*服务熔断*/
    @GetMapping("payment/circuit/get/{id}")
    public  String paymentOk1(@PathVariable Integer id){
        String result = paymenService.paymentCircuitBreaker(id);
        log.info("result:"+result);
        return result;
    }
}
