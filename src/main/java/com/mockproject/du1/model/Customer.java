package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@lombok.Data
@Builder
public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String customerEmail;
    private String dob;
    private int isActivated;
    private String address;
    private String company;
    private String updateBy;
    private LocalDateTime createTimestapm;
    private LocalDateTime updateTimestapm;


}
