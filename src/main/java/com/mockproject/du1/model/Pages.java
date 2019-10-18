package com.mockproject.du1.model;

import lombok.Builder;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

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

    private String updateBy;
    private LocalDateTime createTimestapm;
    private LocalDateTime updateTimestapm;

}
