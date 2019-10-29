package com.mockproject.du1.mapper;


import com.mockproject.du1.model.EmailTemplate;

import java.util.List;

public interface EmailTemplateMapper {

    List<EmailTemplate> sqlGetAllEmailTemplateSelect();

    EmailTemplate sqlGetEmailSelectById(int id);
    EmailTemplate sqlGetEmailTemplateSelectByCampaintId(int id);

    int sqlCreateEmailInsert(EmailTemplate email);
    int sqlEmailUpdate(EmailTemplate email);
}
