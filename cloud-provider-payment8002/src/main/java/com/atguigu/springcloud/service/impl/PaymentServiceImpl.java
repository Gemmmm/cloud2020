package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentMapper;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DELL
 * @date 2020-11-23 19:37
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired(required = false)
    private PaymentMapper mapper;

    @Override
    public int create(Payment payment) {
        return mapper.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return mapper.getPaymentById(id);
    }
}
