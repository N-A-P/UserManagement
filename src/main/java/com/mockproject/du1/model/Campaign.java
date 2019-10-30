package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@lombok.Data
@Builder
public class Campaign {
    /**
     * Campaign Id
     */
    private int campaignId;
    /**
     * Title
     */
    private String title;
    /**
     * Campaign description
     */
    private String description;
    /**
     * Campaign duration
     */
    private int duration;
    /**
     * Campaign start Date
     */
    private Date startDate;
    /**
     * Campaign end Date
     */
    private Date endDate;

    /**
     * updateInfo
     */
    private String updateBy;
    private String createTimestamp;
    private String updateTimestamp;
    /**
     * Campaign email Template Id
     */
    private int emailTemplateId;
}
