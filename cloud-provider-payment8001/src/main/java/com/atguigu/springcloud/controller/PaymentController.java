package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jws.Oneway;
import java.util.List;

/**
 * @author DELL
 * @date 2020-11-23 19:31
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private  String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;
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

    /**
     *
     * @return  自己的微服务信息
     */
    @GetMapping("payment/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("element111:"+service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("element222:"+instance.getServiceId()+" "+instance.getHost()+"" +instance.getPort()+" "+instance.getUri());
        }
        return this.discoveryClient;

    }
    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipKin(){
        return "zipkin,test0101";
    }
}
