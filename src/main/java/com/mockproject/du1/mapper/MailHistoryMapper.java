package com.mockproject.du1.mapper;

import com.mockproject.du1.model.Email;
import com.mockproject.du1.model.MailHistory;

import java.util.List;

public interface MailHistoryMapper {
    List<Email> sqlGetAllMailHistorySelect();

    Email sqlGetMailHistorySelectById();

    int sqlInsertMailHistoryl(MailHistory mailHistory);
}
