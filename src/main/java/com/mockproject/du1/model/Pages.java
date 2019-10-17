package com.mockproject.du1.model;

import lombok.Builder;

@lombok.Data
@Builder
public class Pages {
    private int pagesId;
    private String pagesName;
    private String pagesCode;
    private String pagesUrl;
    private UpdateInfo updateInfo;

}
