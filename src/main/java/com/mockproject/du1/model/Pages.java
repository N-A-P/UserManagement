package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;

@lombok.Data
@Builder
public class Pages {
	/**
	 * Pages Id
	 */
	private int pagesId;
	/**
	 * Pages Name
	 */
	private String pagesName;
	/**
	 * Pages Code
	 */
	private String pagesCode;
	/**
	 * Pages Url
	 */
	private String pagesUrl;
	/**
	 * Update Info
	 */
	@Embedded
	private UpdateInfo updateInfo;

}
