package com.springboot.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/3.
 */
public class Email implements Serializable {

    private String text;
    private String to;
    private String subject;

    public Email(String text, String to, String subject) {
        this.text = text;
        this.to = to;
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


}
