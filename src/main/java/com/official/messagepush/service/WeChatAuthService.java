package com.official.messagepush.service;

import com.official.messagepush.util.SignUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: cww
 * @date: 2021/2/23 10:16
 */
@Service
public class WeChatAuthService {

    @Value("${wechat.token}")
    private String token;

    /**
     * 校验信息
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    public String auth(String signature,String timestamp,String nonce,String echostr){
        Map<String,Object> map = new HashMap<>(16);
        map.put("token",token);
        map.put("timestamp",timestamp);
        map.put("nonce",nonce);
        if (SignUtil.check(map,signature)){
            return echostr;
        }
        return null;
    }
}
