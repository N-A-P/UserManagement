package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;

@lombok.Data
@Builder
public class Customer {
    private int customerId;
    private String customerName;
    private String customerEmail;
    private int isActivated;
    private String address;
    private String company;
    @Embedded
    private UpdateInfo updateInfo;


}
