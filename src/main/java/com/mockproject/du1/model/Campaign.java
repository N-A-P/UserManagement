package com.mockproject.du1.model;

import java.util.Date;

public class Campaign {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.campaign.id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.campaign.title
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private String title;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.campaign.description
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.campaign.duration
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Integer duration;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.campaign.start_date
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Date startDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.campaign.end_date
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Date endDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.campaign.is_activated
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Integer isActivated;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.campaign.updated_by
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private String updatedBy;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.campaign.created_timestamp
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Date createdTimestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.campaign.updated_timestamp
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Date updatedTimestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.campaign.email_tempale_id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Long emailTempaleId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.campaign.id
	 * @return  the value of public.campaign.id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.campaign.id
	 * @param id  the value for public.campaign.id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.campaign.title
	 * @return  the value of public.campaign.title
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.campaign.title
	 * @param title  the value for public.campaign.title
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.campaign.description
	 * @return  the value of public.campaign.description
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.campaign.description
	 * @param description  the value for public.campaign.description
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.campaign.duration
	 * @return  the value of public.campaign.duration
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Integer getDuration() {
		return duration;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.campaign.duration
	 * @param duration  the value for public.campaign.duration
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.campaign.start_date
	 * @return  the value of public.campaign.start_date
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.campaign.start_date
	 * @param startDate  the value for public.campaign.start_date
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.campaign.end_date
	 * @return  the value of public.campaign.end_date
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.campaign.end_date
	 * @param endDate  the value for public.campaign.end_date
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.campaign.is_activated
	 * @return  the value of public.campaign.is_activated
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Integer getIsActivated() {
		return isActivated;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.campaign.is_activated
	 * @param isActivated  the value for public.campaign.is_activated
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setIsActivated(Integer isActivated) {
		this.isActivated = isActivated;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.campaign.updated_by
	 * @return  the value of public.campaign.updated_by
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.campaign.updated_by
	 * @param updatedBy  the value for public.campaign.updated_by
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.campaign.created_timestamp
	 * @return  the value of public.campaign.created_timestamp
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.campaign.created_timestamp
	 * @param createdTimestamp  the value for public.campaign.created_timestamp
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.campaign.updated_timestamp
	 * @return  the value of public.campaign.updated_timestamp
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.campaign.updated_timestamp
	 * @param updatedTimestamp  the value for public.campaign.updated_timestamp
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.campaign.email_tempale_id
	 * @return  the value of public.campaign.email_tempale_id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Long getEmailTempaleId() {
		return emailTempaleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.campaign.email_tempale_id
	 * @param emailTempaleId  the value for public.campaign.email_tempale_id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setEmailTempaleId(Long emailTempaleId) {
		this.emailTempaleId = emailTempaleId;
	}
}