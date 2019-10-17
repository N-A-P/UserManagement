package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;

@lombok.Data
@Builder
public class RolePages {
    private int rolePagesId;
    private int roleId;
    private int pagesId;
    @Embedded
    private UpdateInfo updateInfo;


}
