package com.official.messagepush.bean;

/**
 * @author: cww
 * @date: 2021/2/24 11:24
 */
public class Data {

    private String value;
    private String color;

    public Data(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
