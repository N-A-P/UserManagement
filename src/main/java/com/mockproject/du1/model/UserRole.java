package com.mockproject.du1.model;

import lombok.Builder;

@lombok.Data
@Builder
public class UserRole {
    private int userRoleId;
    private int userId;
    private int roleId;
    private String joinDate;
    private String leaveDate;
    private UpdateInfo updateInfo;

}
