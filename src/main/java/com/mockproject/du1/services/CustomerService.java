package com.mockproject.du1.services;

import com.mockproject.du1.mapper.CustomerMapper;
import com.mockproject.du1.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerMapper customerMapper;
    public List<Customer> getAllCustomer(){
        try{
            return customerMapper.sqlGetAllCustomer();
        }catch (Exception e){
            return null;
        }
    }
    public String editCustomer(Customer customer){
        try{
             customerMapper.updateCustomer(customer);
             return"Success";
        }catch (Exception e){

            return e.getMessage();
        }
    }
    public String deleteCustomer(int id){
        try{
            customerMapper.updateCustomer(Customer.builder().id(id).isActivated(0).build());
            return"Success";
        }catch (Exception e){

            return e.getMessage();
        }
    }
}
