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
	 * @return Max Department Id
	 */
	List<EmployeeOfDepartment> sqlGetListEmployeeOfDepartmentByStatus(@Param("department_id") int department_id,
			@Param("role_id") int role_id, @Param("statusDepartmentDetail") int statusDepartmentDetail,
			@Param("statusDepartment") int statusDepartment);

	/**
	 * Update Department Information
	 *
	 */
	int sqlDepartmentInfoUpdate(Department department);

	/**
	 * Update Department Status
	 *
	 */
	int sqlDepartmentStatusUpdate(Department department);

	/**
	 * Insert Department Information
	 *
	 */
	int sqlDepartmentInsert(Department department);

}
