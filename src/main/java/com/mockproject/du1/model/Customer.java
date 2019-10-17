package com.mockproject.du1.model;

import lombok.Builder;

@lombok.Data
@Builder
public class Customer {
    private int customerId;
    private String customerName;
    private String customerEmail;
    private int isActivated;
    private String address;
    private String company;
    private UpdateInfo updateInfo;


}
