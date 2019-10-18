package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

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
    private String updateBy;
    private LocalDateTime createTimestapm;
    private LocalDateTime updateTimestapm;


}
