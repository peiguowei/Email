package com.demo.email.type;

/**
 * 邮箱的状态
 */
public enum EmailState {
    WAIT("待发送"),SUCCESS("发送成功");
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    EmailState(String desc) {
        this.desc = desc;
    }
}
