package com.official.messagepush.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;

/**
 * @author: cww
 * @date: 2021/2/23 10:56
 */
public class SignUtil {


    public static boolean check(String signStr,String orgSign){
        String sign = DigestUtils.sha1Hex(signStr);
        if (sign.equals(orgSign)){
            return true;
        }
        return false;
    }

    public static boolean check(Map<String,? extends Object> signMap, String orgSign){
        Set<Map.Entry<String, Object>> entrySet = ((Map<String, Object>)signMap).entrySet();
        ArrayList<Map.Entry<String, Object>> paramNames = new ArrayList<>(entrySet);
        Collections.sort(paramNames, Map.Entry.comparingByKey());
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry:paramNames){
            sb.append(entry.getValue());
        }
        return check(sb.toString(),orgSign);
    }

}
