package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;

@lombok.Data
@Builder
public class UserRole {
    private int userRoleId;
    private int userId;
    private int roleId;
    private String joinDate;
    private String leaveDate;
    @Embedded
    private UpdateInfo updateInfo;

}
