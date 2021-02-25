package com.official.messagepush.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 获取微信openid
 *
 * @author: cww
 * @date: 2021/2/25 11:06
 */
@Service
public class GetOpenIdService {


    @Value("")
    private String weChatAuthorizeUrl;
    @Value("")
    private String appId;
    @Value("")
    private String authorizeRedirectUrl;
    private final static Logger LOGGER = LoggerFactory.getLogger(GetOpenIdService.class);

    /**
     * 跳转微信授权路径
     *
     * @param redirectUrl
     * @return
     */
    public String wxAuthorizeUrl(String redirectUrl) {
        Assert.hasText(redirectUrl, "回调地址不能为空");
        Assert.isTrue(redirectUrl.length() < 512, "回调地址不能超过512个字符");
        StringBuilder s = new StringBuilder(512);
        s.append(weChatAuthorizeUrl);
        s.append("?appid=");
        s.append(appId);
        s.append("&redirect_uri=");
        String targetUrl = null;
        try {
            targetUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        s.append(targetUrl);
        s.append("&response_type=code&scope=snsapi_base&state=#wechat_redirect");
        return s.toString();
    }

    /**
     * 处理响应重定向
     *
     * @param response
     */
    public void dealRespRedirect(HttpServletResponse response) {
        try {
            response.sendRedirect(response.encodeRedirectURL(wxAuthorizeUrl(authorizeRedirectUrl)));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


}
