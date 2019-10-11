package com.mockproject.du1.model;

import lombok.Builder;

@lombok.Data
@Builder
public class Email {
    private int emailId;
    private String content;
    private String subject;

    public Email(int emailId, String content, String subject) {
        this.emailId = emailId;
        this.content = content;
        this.subject = subject;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
