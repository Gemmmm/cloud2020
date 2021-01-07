package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.MessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author DELL
 * @date 2020-12-30 9:22
 */
@RestController
public class SendMessageController {

    @Resource
    private MessageProvider messageProvider;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return messageProvider.send();
    }
}
