package com.mockproject.du1.model;

import java.util.Date;

public class EmailTemplate {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.email_template.id
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.email_template.title
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.email_template.body
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    private String body;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.email_template.updated_by
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    private String updatedBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.email_template.created_timestamp
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    private Date createdTimestamp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.email_template.updated_timestamp
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    private Date updatedTimestamp;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.email_template.id
     *
     * @return the value of public.email_template.id
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.email_template.id
     *
     * @param id the value for public.email_template.id
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.email_template.title
     *
     * @return the value of public.email_template.title
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.email_template.title
     *
     * @param title the value for public.email_template.title
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.email_template.body
     *
     * @return the value of public.email_template.body
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    public String getBody() {
        return body;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.email_template.body
     *
     * @param body the value for public.email_template.body
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.email_template.updated_by
     *
     * @return the value of public.email_template.updated_by
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.email_template.updated_by
     *
     * @param updatedBy the value for public.email_template.updated_by
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.email_template.created_timestamp
     *
     * @return the value of public.email_template.created_timestamp
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.email_template.created_timestamp
     *
     * @param createdTimestamp the value for public.email_template.created_timestamp
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.email_template.updated_timestamp
     *
     * @return the value of public.email_template.updated_timestamp
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    public Date getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.email_template.updated_timestamp
     *
     * @param updatedTimestamp the value for public.email_template.updated_timestamp
     *
     * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
     */
    public void setUpdatedTimestamp(Date updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }
}