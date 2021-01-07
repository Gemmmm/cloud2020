package com.atguigu.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DELL
 * @date 2020-11-23 18:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private String code;
    private String message;
    private T data;

    private CommonResult(String code, String message) {
        this(code, message, null);
    }
}
