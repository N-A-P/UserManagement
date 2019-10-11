package com.mockproject.du1.mapper;


import com.mockproject.du1.model.Email;

import java.util.List;

public interface EmailMapper {

    List<Email> sqlGetAllEmailSelect();

    Email sqlGetEmailSelectById();

    int sqlCreateEmailInsert(Email email);
}
