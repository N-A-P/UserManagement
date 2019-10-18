package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@lombok.Data
@Builder
public class UserRole {
    private int userRoleId;
    private int userId;
    private int roleId;
    private String joinDate;
    private String leaveDate;
    private String updateBy;
    private LocalDateTime createTimestapm;
    private LocalDateTime updateTimestapm;

}
