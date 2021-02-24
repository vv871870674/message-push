package com.official.messagepush.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 模板消息请求bean
 *
 * @author: cww
 * @date: 2021/2/23 17:24
 */
public class MessageRequestBean {

    /**
     * 接收者openid
     */
    private String touser;

    /**
     * 模板ID
     */
    @JSONField(name = "template_id")
    private String templateId;

    /**
     * 模板跳转链接（海外帐号没有跳转能力）
     */
    private String url;

    /**
     * 模板数据
     */
    private MessageData data;

    /**
     * 模板内容字体颜色，不填默认为黑色
     */
    private String color;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MessageData getData() {
        return data;
    }

    public void setData(MessageData data) {
        this.data = data;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
