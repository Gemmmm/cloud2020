package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author DELL
 * @date 2020-11-23 18:48
 */
@Mapper
public interface PaymentMapper {
    int create(@Param("payment") Payment payment);
    Payment getPaymentById(@Param("id") Long id);

}
