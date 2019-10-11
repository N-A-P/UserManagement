package com.mockproject.du1.model;

import lombok.Builder;

@lombok.Data
@Builder
public class Email {
    private int emailId;
    private String content;
    private String subject;


}
