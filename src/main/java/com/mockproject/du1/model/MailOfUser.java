package com.mockproject.du1.model;

import java.util.List;

@lombok.Data
public class MailOfUser {
    List<Users> users;
    String emailHeader;
    String emailBodyText;
}
