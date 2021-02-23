package com.official.messagepush.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: cww
 * @date: 2021/2/23 17:46
 */
@Service
public class SendMessageService {

    @Value("${wechat.message.send.url}")
    private String url;

    private void send(String token){

    }
}
