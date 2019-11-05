package com.mockproject.du1.model;

import java.util.Date;

public class Department {
	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column public.department.id
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	private Long departmentId;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column public.department.code
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	private String code;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column public.department.name
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	private String name;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column public.department.number_of_employee
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	private Integer numberOfEmployee;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column public.department.is_activated
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	private Integer isActivated;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column public.department.updated_by
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	private String updatedBy;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column public.department.created_timestamp
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	private Date createdTimestamp;

	/**
	 *
	 * This field was generated by MyBatis Generator. This field corresponds to the
	 * database column public.department.updated_timestamp
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	private Date updatedTimestamp;

	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column public.department.id
	 *
	 * @return the value of public.department.id
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public Long getDepartmentId() {
		return departmentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column public.department.id
	 *
	 * @param id the value for public.department.id
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public void setDepartmentId(Long id) {
		this.departmentId = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column public.department.code
	 *
	 * @return the value of public.department.code
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public String getCode() {
		return code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column public.department.code
	 *
	 * @param code the value for public.department.code
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column public.department.name
	 *
	 * @return the value of public.department.name
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column public.department.name
	 *
	 * @param name the value for public.department.name
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column public.department.number_of_employee
	 *
	 * @return the value of public.department.number_of_employee
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public Integer getNumberOfEmployee() {
		return numberOfEmployee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column public.department.number_of_employee
	 *
	 * @param numberOfEmployee the value for public.department.number_of_employee
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public void setNumberOfEmployee(Integer numberOfEmployee) {
		this.numberOfEmployee = numberOfEmployee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column public.department.is_activated
	 *
	 * @return the value of public.department.is_activated
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public Integer getIsActivated() {
		return isActivated;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column public.department.is_activated
	 *
	 * @param isActivated the value for public.department.is_activated
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public void setIsActivated(Integer isActivated) {
		this.isActivated = isActivated;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column public.department.updated_by
	 *
	 * @return the value of public.department.updated_by
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column public.department.updated_by
	 *
	 * @param updatedBy the value for public.department.updated_by
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column public.department.created_timestamp
	 *
	 * @return the value of public.department.created_timestamp
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column public.department.created_timestamp
	 *
	 * @param createdTimestamp the value for public.department.created_timestamp
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value
	 * of the database column public.department.updated_timestamp
	 *
	 * @return the value of public.department.updated_timestamp
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of
	 * the database column public.department.updated_timestamp
	 *
	 * @param updatedTimestamp the value for public.department.updated_timestamp
	 *
	 * @mbg.generated Mon Nov 04 16:54:30 ICT 2019
	 */
	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}
}