package com.mockproject.du1.mapper;

import com.mockproject.du1.model.CampaignCustomer;

import java.util.List;

public interface CampaignCustomerMapper {
    List<CampaignCustomer> sqlGetAllMailHistorySelect();

    CampaignCustomer sqlGetMailHistorySelectByEmail(String email);

    int sqlInsertMailHistoryl(CampaignCustomer mailHistory);
}
