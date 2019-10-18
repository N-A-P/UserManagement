package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@lombok.Data
@Builder
public class Pages {
    private int pagesId;
    private String pagesName;
    private String pagesCode;
    private String pagesUrl;
    private String updateBy;
    private LocalDateTime createTimestapm;
    private LocalDateTime updateTimestapm;

}
