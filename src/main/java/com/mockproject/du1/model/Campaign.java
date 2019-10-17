package com.mockproject.du1.model;

import lombok.Builder;

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
    private UpdateInfo updateInfo;


}
