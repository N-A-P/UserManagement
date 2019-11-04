package com.mockproject.du1.model;

import java.util.List;

@lombok.Data
public class EmployeeOfDepartmentResponse {
	/**
	 * 
	 */
	private List<EmployeeOfDepartment> employeeOfDepartmentsError;

	/**
	 * 
	 */
	private List<EmployeeOfDepartment> employeeOfDepartmentsReturn;

}
