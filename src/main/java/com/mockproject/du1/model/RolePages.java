package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@lombok.Data
@Builder
public class RolePages {
    private int rolePagesId;
    private int roleId;
    private int pagesId;
    private String updateBy;
    private LocalDateTime createTimestapm;
    private LocalDateTime updateTimestapm;


}
