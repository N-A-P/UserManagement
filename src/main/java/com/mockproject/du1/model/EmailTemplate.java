package com.mockproject.du1.model;

import lombok.Builder;

@lombok.Data
@Builder
public class EmailTemplate {
    private int emailId;
    private String title;
    private String body;
    private UpdateInfo updateInfo;




    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int value) {
        this.emailId = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
