package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@lombok.Data
@Builder
public class Role {
    private int roleId;
    private String roleName;
    private String roleEmail;
    private String updateBy;
    private LocalDateTime createTimestapm;
    private LocalDateTime updateTimestapm;

}
