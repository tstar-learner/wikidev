package com.jiawa.wikidev.exception;

public enum BusinessExceptionCode {
    USER_LOGIN_NAME_EXTI("登录名已存在"),
    ;
    private String desc;
    BusinessExceptionCode(String desc){
        this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
