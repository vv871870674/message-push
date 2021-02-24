package com.official.messagepush.service;

import com.alibaba.fastjson.JSONObject;
import com.official.messagepush.bean.Data;
import com.official.messagepush.bean.MessageData;
import com.official.messagepush.bean.MessageRequestBean;
import com.official.messagepush.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: cww
 * @date: 2021/2/23 17:46
 */
@Service
public class SendMessageService {

    @Value("${wechat.message.send.url}")
    private String url;
    @Value("${wechat.message.id}")
    private String templateId;
    /**
     * 微信access_token
     */
    private final static String TOKEN = "token";
    /**
     * 用户openId
     */
    private final static String OPEN_ID = "openId";
    /**
     * 用户手机号码
     */
    private final static String TEL = "tel";
    /**
     * 用户姓名
     */
    private final static String NAME = "name";
    /**
     * 用户注册时间
     */
    private final static String REGISTER_TIME = "registerTime";
    private final static Logger LOGGER = LoggerFactory.getLogger(SendMessageService.class);
    private final static String SUCCESS_VALUE = "0";
    private final static String ERR_CODE_KEY = "errcode";

    public boolean send(Map<String,String> messageMap){
        MessageRequestBean requestBean = new MessageRequestBean();
        requestBean.setTouser(messageMap.get(OPEN_ID));
        requestBean.setTemplateId(templateId);
        MessageData data = new MessageData();
        data.setFirst(new Data("你好，有新用户在您的推荐下已成功在平台注册","#173177"));
        data.setKeyword1(new Data(messageMap.get(NAME),"#173177"));
        data.setKeyword2(new Data(messageMap.get(TEL),"#173177"));
        data.setKeyword3(new Data(messageMap.get(REGISTER_TIME),"#173177"));
        data.setRemark(new Data("感谢您的鼎力支持","#173177"));
        requestBean.setData(data);
        String params = JSONObject.toJSONString(requestBean);
        String response = HttpUtil.postJson(url + messageMap.get(TOKEN),params);
        LOGGER.info("消息模板推送结果:{}",response);
        JSONObject resp = JSONObject.parseObject(response);
        if (resp != null && SUCCESS_VALUE.equals(resp.getString(ERR_CODE_KEY))){
            return true;
        }
        return false;
    }
}
