package com.mockproject.du1.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.mockproject.du1.model.Department;
import com.mockproject.du1.model.EmployeeOfDepartment;
import com.mockproject.du1.model.UserDepartment;

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
	Department sqlGetDepartmentByIdSelect(int departmentId);

	/**
	 * Get Max Department
	 *
	 * @return Max Department Id
	 */
	long sqlGetMaxDepartmentIdSelect();

	/**
	 * Count Number Of Department By Name
	 *
	 * @return Count Number Of Department By Name
	 */
	long sqlCountDepartmentByNameSelect(String departmentName);

	/**
	 * Count Number Of Department By Code
	 *
	 * @return Count Number Of Department By Code
	 */
	long sqlCountDepartmentByCodeSelect(String departmentCode);

	/**
	 * Get Max Department
	 *
	 * @return List Employee Of Department
	 */
	List<EmployeeOfDepartment> sqlGetListEmployeeOfDepartmentByStatus(@Param("departmentId") int departmentId,
			@Param("statusUserDepartment") int statusUserDepartment, @Param("statusDepartment") int statusDepartment,
			@Param("statusUser") int statusUser, int action);

	/**
	 * Update Department Information
	 *
	 */
	int sqlDepartmentInfoUpdate(Department department);

	/**
	 * Update User Department Information
	 *
	 */
	int sqlRemoveEmployeeForDeparmentUpdate(UserDepartment userDepartment);

	/**
	 * Update Department Status
	 *
	 */
	int sqlDepartmentStatusUpdate(@Param("departmentId") int departmentId, @Param("isActivated") int isActivated,
			@Param("updateBy") String updateBy, @Param("updateTimestamp") String updateTimestamp);

	/**
	 * Insert Department Information
	 *
	 */
	int sqlDepartmentInsert(Department department);

	/**
	 * Insert New Employee For Deparment
	 *
	 */
	int sqlNewEmployeeForDeparmentInsert(UserDepartment userDepartment);

}
