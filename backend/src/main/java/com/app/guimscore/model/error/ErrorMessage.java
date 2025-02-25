package com.app.guimscore.model.error;

public class ErrorMessage {
    private String title;
    private String message;
    private Integer status;

    public ErrorMessage(String title, String message, Integer status) {
        this.title = title;
        this.message = message;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
