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
	private int emaiTemplateId;
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
    private LocalDateTime createTimestapm;
    private LocalDateTime updateTimestapm;

}
