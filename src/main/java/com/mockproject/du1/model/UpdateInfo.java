package com.mockproject.du1.model;

import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

@lombok.Data
@Builder
public class UpdateInfo implements Serializable {
    private String updateBy;
    private LocalDateTime createTimestapm;
    private LocalDateTime updateTimestapm;



}
