package com.official.messagepush.controller;

import com.official.messagepush.service.WeChatAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author: cww
 * @date: 2021/2/23 10:07
 */
@Controller
@RequestMapping("/weChat")
public class WeChatAuthController {

    @Resource
    WeChatAuthService weChatAuthService;

    @RequestMapping("/auth")
    public String auth(String signature,String timestamp,String nonce,String echostr){
        return weChatAuthService.auth(signature,timestamp,nonce,echostr);
    }
}
