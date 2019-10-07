package com.mockproject.du1.mapper;

import java.util.List;
import com.mockproject.du1.model.Users;
import com.mockproject.du1.model.Department;

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
	 * Update Department Information
	 *
	 */
	void sqlDepartmentInfoUpdate(Department department);

	/**
	 * Update Department Information
	 *
	 */
	void sqlDepartmentStatusUpdate(Department department);

	/**
	 * Insert Department Information
	 *
	 */
	void sqlDepartmentInsert(Department department);

}
