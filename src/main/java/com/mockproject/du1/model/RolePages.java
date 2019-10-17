package com.mockproject.du1.model;

import lombok.Builder;

@lombok.Data
@Builder
public class RolePages {
    private int rolePagesId;
    private int roleId;
    private int pagesId;
    private UpdateInfo updateInfo;


}
