package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;

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
	@Embedded
	private UpdateInfo updateInfo;

}
