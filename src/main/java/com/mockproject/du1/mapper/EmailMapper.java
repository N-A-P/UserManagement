package com.mockproject.du1.mapper;


import com.mockproject.du1.model.EmailTemplate;

import java.util.List;

public interface EmailMapper {

    List<EmailTemplate> sqlGetAllEmailSelect();

    EmailTemplate sqlGetEmailSelectById(int id);

    int sqlCreateEmailInsert(EmailTemplate email);
    int sqlEmailUpdate(EmailTemplate email);
}
