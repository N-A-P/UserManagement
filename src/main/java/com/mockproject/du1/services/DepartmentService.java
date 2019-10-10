package com.mockproject.du1.services;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mockproject.du1.mapper.DepartmentMapper;
import com.mockproject.du1.model.Department;
import com.mockproject.du1.model.EmployeeOfDepartment;

@Service
public class DepartmentService {
	private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);
	/**
	 * 
	 */
	private static final int STATUS_STAY = 1;
	/**
	 * 
	 */
	private static final int STATUS_LEAVE = 0;
	/**
	 * 
	 */
	private static final int ACTION_ADD = 1;
	/**
	 * 
	 */
	private static final int ACTION_DELETE = 2;

	/**
	 * 
	 */
	private static final int ACTIVE = 1;

	/**
	 * 
	 */
	private static final int INACTIVE = 0;

	/**
	 * 
	 */
	private static final int ROLE_EMPLOYEE = 3;
	/**
	 * 
	 */
	@Autowired
	private DepartmentMapper departmentMapper;

	/**
	 * 
	 */
	public List<Department> getAllListDepartment() {

		return departmentMapper.sqlGetAllDepartmentSelect();
	}

	/**
	 * 
	 */
	public List<Department> getListDepartmentActive() {

		return departmentMapper.sqlGetDepartmentByStatusSelect(STATUS_STAY);
	}

	/**
	 * 
	 */
	public List<Department> getListDepartmentInActive() {

		return departmentMapper.sqlGetDepartmentByStatusSelect(STATUS_LEAVE);
	}

	/**
	 * 
	 */
	public Department getDepartmentById(int departmentId) {

		return departmentMapper.sqlGetDepartmentByIdSelect(departmentId);
	}

	/**
	 * 
	 */
	public long getMaxDepartmentIdSelect() {

		return departmentMapper.sqlGetMaxDepartmentIdSelect();
	}

	/**
	 * 
	 */
	public List<EmployeeOfDepartment> getListEmployeeOfDepartment(int department_id) {

		return departmentMapper.sqlGetListEmployeeOfDepartmentByStatus(department_id, ROLE_EMPLOYEE, STATUS_STAY,
				ACTIVE, ACTION_DELETE);
	}

	/**
	 * 
	 */
	public List<EmployeeOfDepartment> getListEmployeeNotInDepartment(int department_id) {

		return departmentMapper.sqlGetListEmployeeOfDepartmentByStatus(department_id, ROLE_EMPLOYEE, STATUS_LEAVE,
				ACTIVE, ACTION_ADD);
	}

	/** 
	 * 
	 */
	public int departmentInfoUpdate(Department department) {
		try {
			return departmentMapper.sqlDepartmentInfoUpdate(department);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return 0;

	}

	/**
	 * 
	 */
	public int activeDepartment(int departmentId) {
		try {
			return departmentMapper.sqlDepartmentStatusUpdate(departmentId, ACTIVE);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return 0;
	}

	/**
	 * 
	 */
	public int inActiveDepartment(int departmentId) {
		try {
			return departmentMapper.sqlDepartmentStatusUpdate(departmentId, INACTIVE);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return 0;
	}
//	/**
//	 * 
//	 */
//	public int addEmployeeToDepartment(int departmentDetailId) {
//		try {
//			return departmentMapper.sqlDepartmentDetailStatusUpdate(departmentDetailId, STATUS_STAY);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
//
//		return 0;
//	}
//
	/**
	 * 
	 */
	public int removeEmployeeFromDepartment(int departmentDetailId) {
		try {
			return departmentMapper.sqlDepartmentDetailStatusUpdate(departmentDetailId, STATUS_LEAVE);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return 0;
	}

	/**
	 * 
	 */
	public int departmentInsert(Department department) {
		try {
			return departmentMapper.sqlDepartmentInsert(department);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * 
	 */
	public int newEmployeeForDeparmentInsert(EmployeeOfDepartment employeeOfDepartment) {
		try {
			return departmentMapper.sqlNewEmployeeForDeparmentInsert(employeeOfDepartment, STATUS_STAY);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
}
