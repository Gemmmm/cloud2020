package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author DELL
 * @date 2020-11-23 19:31
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private  String serverPort;
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result= paymentService.create(payment);
        log.info("插入结果"+result);
        if(result>0){
            return new CommonResult("200","插入数据库成功,serverPort:"+serverPort,result);
        }else{
            return new CommonResult("444","插入数据库失败",null);
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult get(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果"+payment);
        if(payment!=null){
            return new CommonResult("200","查询数据库成功,serverPort:"+serverPort,payment);
        }else{
            return new CommonResult("000","没有记录，Id:"+id,null);
        }
    }
    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
