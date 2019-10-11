package com.mockproject.du1.model;

import lombok.Builder;

import java.util.Date;

@lombok.Data
@Builder
public class MailHistory {
    private int mailHistoryId;
    private Date SendDate;
    private int EmailId;
    private int UserId;
}
