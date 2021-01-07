package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author DELL
 * @date 2020-12-22 13:06
 */
@Service
public class PaymenService {

    public String PaymetInfo(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "paymentInfoOK+id:" + id;
    }

    @HystrixCommand(fallbackMethod = "myTimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")
    })
    public String PaymetInfo2(Integer id) {
        int timeNumber = 3000;
        try {
            Thread.sleep(timeNumber);
        } catch (InterruptedException e) {
        }
        return "线程池" + Thread.currentThread().getName() + "paymentInfoERROR+id:" + id + "timeNumber:" + timeNumber;
    }

    public String myTimeOutHandler(Integer id){
        return  "拒绝访问";
    }



    /*服务熔断*/
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value="true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value="10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少跳闸
    })
    public String paymentCircuitBreaker(@PathVariable Integer id){
        if(id<0){
            throw  new RuntimeException("id不能为负数");
        }
        String serialNumber= IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"调用成功,"+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable Integer id){
        return " id不能为负数，id:"+id;
    }
}
