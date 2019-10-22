package com.mockproject.du1.mapper;


import com.mockproject.du1.model.Customer;

import java.util.List;

public interface CustomerMapper {
    int sqlCreateCustomerInsert(Customer customer);
    Customer sqlGetCustomerByEmailSelect(String email);
    List<Customer> sqlGetAllCustomer();
    List<Customer> sqlGetCustomerByCampaignAndMaxTime(int CampaignId);

}
