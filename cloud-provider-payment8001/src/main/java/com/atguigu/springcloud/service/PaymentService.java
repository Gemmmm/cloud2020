package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author DELL
 * @date 2020-11-23 19:37
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(Long id);

}
