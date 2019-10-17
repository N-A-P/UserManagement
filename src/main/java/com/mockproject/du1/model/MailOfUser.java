package com.mockproject.du1.model;

import lombok.Builder;

import java.util.List;

@lombok.Data
@Builder
public class MailOfUser {
	/**
	 * List User
	 */
    List<Users> users;
    /**
	 * Email Header
	 */
    String emailHeader;
    /**
	 * Email Body Text
	 */
    String emailBodyText;

}
