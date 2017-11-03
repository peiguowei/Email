package com.demo.email.entity;

import java.util.Date;

/**
 * 邮箱信息
 */
public class EmailMessage {
    private Integer id;
    private Integer sendEmailId;//发件人id
    private Integer collectEmailId;//收件人id
    private String title;//主题
    private String content;//内容
    private String state;//状态
    private Date sendDate;//发送日期
    private Date readDate;//阅读日期
    private String sendName;//发件人姓名
    private String collectName;//收件人姓名

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSendEmailId() {
        return sendEmailId;
    }

    public void setSendEmailId(Integer sendEmailId) {
        this.sendEmailId = sendEmailId;
    }

    public Integer getCollectEmailId() {
        return collectEmailId;
    }

    public void setCollectEmailId(Integer collectEmailId) {
        this.collectEmailId = collectEmailId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }
}
