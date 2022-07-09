package com.jiawa.wikidev.resp;

public class CommonResp<T> {
    private T content;
    private boolean success=true;
    private String message;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommonResp{" +
                "content=" + content +
                ", success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
