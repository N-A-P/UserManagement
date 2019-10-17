package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.util.Date;

@lombok.Data
@Builder
public class CampaignCustomer {
    private int campaignCustomerId;
    private Date sendTime;
    private int sendBy;
    private Date joinDate;
    private int campaignId;
    private int customerId;
    @Embedded
    private UpdateInfo updateInfo;

}
