package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;

@lombok.Data
@Builder
public class Campaign {
    private int campaignId;
    private String title;
    private String description;
    private int duration;
    private String startDate;
    private String endDate;
    private int emailTemplateId;
    @Embedded
    private UpdateInfo updateInfo;


}
