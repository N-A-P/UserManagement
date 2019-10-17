package com.mockproject.du1.model;

import lombok.Builder;

@lombok.Data
@Builder
public class Role {
    private int roleId;
    private String roleName;
    private String roleEmail;
    private UpdateInfo updateInfo;

}
