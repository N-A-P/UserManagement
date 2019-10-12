package com.mockproject.du1.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.mockproject.du1.model.Department;
import com.mockproject.du1.model.EmployeeOfDepartment;

public interface DepartmentMapper {
	/**
	 * Get All Department
	 *
	 * @return All Department
	 */
	List<Department> sqlGetAllDepartmentSelect();

	/**
	 * Get Department By Status
	 *
	 * @return Department
	 */
	List<Department> sqlGetDepartmentByStatusSelect(int status);

	/**
	 * Get Department By Id
	 *
	 * @return Department
	 */
	Department sqlGetDepartmentByIdSelect(int department_id);

	/**
	 * Get Max Department
	 *
	 * @return Max Department Id
	 */
	long sqlGetMaxDepartmentIdSelect();

	/**
	 * Get Max Department
	 *
	 * @return List Employee Of Department
	 */
	List<EmployeeOfDepartment> sqlGetListEmployeeOfDepartmentByStatus(@Param("department_id") int department_id,
			@Param("role_id") int role_id, @Param("statusDepartmentDetail") int statusDepartmentDetail,
			@Param("statusDepartment") int statusDepartment, @Param("statusUser") int statusUser, int action);

	/**
	 * Update Department Information
	 *
	 */
	int sqlDepartmentInfoUpdate(Department department);

	/**
	 * Update Department Information
	 *
	 */
	int sqlDepartmentDetailStatusUpdate(@Param("departmentDetailId") int departmentDetailId,
			@Param("status") int status);

	/**
	 * Update Department Status
	 *
	 */
	int sqlDepartmentStatusUpdate(@Param("departmentId") int departmentId, @Param("status") int status);

	/**
	 * Insert Department Information
	 *
	 */
	int sqlDepartmentInsert(Department department);

	/**
	 * Insert New Employee For Deparment
	 *
	 */
	int sqlNewEmployeeForDeparmentInsert(@Param("EmployeeOfDepartment") EmployeeOfDepartment employeeOfDepartment,
			@Param("status") int status);

}
