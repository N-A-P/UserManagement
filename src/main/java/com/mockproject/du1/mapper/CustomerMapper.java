package com.mockproject.du1.mapper;


import com.mockproject.du1.model.Customer;

public interface CustomerMapper {



    int sqlCreateCustomerInsert(Customer customer);
    Customer sqlGetCustomerByEmailSelect(String email);
}
