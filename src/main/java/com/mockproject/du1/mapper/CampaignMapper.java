package com.mockproject.du1.mapper;


import com.mockproject.du1.model.Campaign;
import com.mockproject.du1.model.Customer;

import java.util.List;

public interface CampaignMapper {
    int sqlCreateCampaignInsert(Campaign campaign);
    List<Campaign> sqlGetAllCampain();
    int sqlCampaignInfoUpdate(Campaign campaign);
    Campaign sqlGetCampainById(Campaign campaign);

}
