package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;

@lombok.Data
@Builder
public class Role {
    private int roleId;
    private String roleName;
    private String roleEmail;
    @Embedded
    private UpdateInfo updateInfo;

}
