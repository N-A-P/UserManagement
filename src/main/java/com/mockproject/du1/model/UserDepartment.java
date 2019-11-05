package com.mockproject.du1.model;

import java.util.Date;

public class UserDepartment {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.user_department.id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.user_department.join_date
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Date joinDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.user_department.leave_date
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Date leaveDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.user_department.stay_or_leave
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Integer stayOrLeave;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.user_department.updated_by
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private String updatedBy;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.user_department.created_timestamp
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Date createdTimestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.user_department.updated_timestamp
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Date updatedTimestamp;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.user_department.department_id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Long departmentId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.user_department.user_id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Long userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column public.user_department.role_id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	private Long roleId;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.user_department.id
	 * @return  the value of public.user_department.id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.user_department.id
	 * @param id  the value for public.user_department.id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.user_department.join_date
	 * @return  the value of public.user_department.join_date
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Date getJoinDate() {
		return joinDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.user_department.join_date
	 * @param joinDate  the value for public.user_department.join_date
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.user_department.leave_date
	 * @return  the value of public.user_department.leave_date
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Date getLeaveDate() {
		return leaveDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.user_department.leave_date
	 * @param leaveDate  the value for public.user_department.leave_date
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.user_department.stay_or_leave
	 * @return  the value of public.user_department.stay_or_leave
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Integer getStayOrLeave() {
		return stayOrLeave;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.user_department.stay_or_leave
	 * @param stayOrLeave  the value for public.user_department.stay_or_leave
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setStayOrLeave(Integer stayOrLeave) {
		this.stayOrLeave = stayOrLeave;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.user_department.updated_by
	 * @return  the value of public.user_department.updated_by
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.user_department.updated_by
	 * @param updatedBy  the value for public.user_department.updated_by
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.user_department.created_timestamp
	 * @return  the value of public.user_department.created_timestamp
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.user_department.created_timestamp
	 * @param createdTimestamp  the value for public.user_department.created_timestamp
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.user_department.updated_timestamp
	 * @return  the value of public.user_department.updated_timestamp
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.user_department.updated_timestamp
	 * @param updatedTimestamp  the value for public.user_department.updated_timestamp
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.user_department.department_id
	 * @return  the value of public.user_department.department_id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Long getDepartmentId() {
		return departmentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.user_department.department_id
	 * @param departmentId  the value for public.user_department.department_id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.user_department.user_id
	 * @return  the value of public.user_department.user_id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.user_department.user_id
	 * @param userId  the value for public.user_department.user_id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column public.user_department.role_id
	 * @return  the value of public.user_department.role_id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column public.user_department.role_id
	 * @param roleId  the value for public.user_department.role_id
	 * @mbg.generated  Mon Nov 04 21:55:34 ICT 2019
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}