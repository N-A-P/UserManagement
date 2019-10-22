package com.mockproject.du1.model;

import lombok.Builder;

import java.util.List;

@lombok.Data
@Builder
public class CampaignDetail {
	Campaign campaign;
	List<Customer> customerList;
	List<Integer> customerCheck;
}
