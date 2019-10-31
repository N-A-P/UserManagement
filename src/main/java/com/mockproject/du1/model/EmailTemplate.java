package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

@lombok.Data
@Builder
public class EmailTemplate {
	/**
	 * Email Template Id
	 */
	private int emailTemplateId;
	/**
	 * Email Template title
	 */
	private String title;
	/**
	 * Email Template body
	 */
	private String body;
	/**
	 * Update Info
	 */
    private String updateBy;
    private String createTimestamp;
    private String updateTimestamp;

}
