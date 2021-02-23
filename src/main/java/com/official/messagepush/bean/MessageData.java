package com.official.messagepush.bean;


/**
 * 模板消息具体内容
 *
 * @author: cww
 * @date: 2021/2/23 17:38
 */
public class MessageData {

    private Data first;

    private Data keyword1;

    private Data keyword2;

    private Data remark;

    public Data getFirst() {
        return first;
    }

    public void setFirst(Data first) {
        this.first = first;
    }

    public Data getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(Data keyword1) {
        this.keyword1 = keyword1;
    }

    public Data getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(Data keyword2) {
        this.keyword2 = keyword2;
    }

    public Data getRemark() {
        return remark;
    }

    public void setRemark(Data remark) {
        this.remark = remark;
    }

    class Data{
        private String value;
        private String color;

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



}
