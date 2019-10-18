package com.mockproject.du1.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mockproject.du1.mapper.DepartmentMapper;
import com.mockproject.du1.model.Department;
import com.mockproject.du1.model.EmployeeOfDepartment;
import com.mockproject.du1.model.UpdateInfo;

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
	private static final int NUMBER_OF_EMPLOYEE_ZERO = 0;
	/**
	 * 
	 */
	@Autowired
	private DepartmentMapper departmentMapper;

	/**
	 * 
	 */
	public List<Department> getAllListDepartment() {
		try {
			return departmentMapper.sqlGetAllDepartmentSelect();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;

	}

	/**
	 * 
	 */
	public List<Department> getListDepartmentActive() {
		try {
			return departmentMapper.sqlGetDepartmentByStatusSelect(STATUS_STAY);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/**
	 * 
	 */
	public List<Department> getListDepartmentInActive() {
		try {
			return departmentMapper.sqlGetDepartmentByStatusSelect(STATUS_LEAVE);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/**
	 * 
	 */
	public Department getDepartmentById(int departmentId) {
		try {
			return departmentMapper.sqlGetDepartmentByIdSelect(departmentId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/**
	 * 
	 */
	public long getMaxDepartmentIdSelect() {
		try {
			return departmentMapper.sqlGetMaxDepartmentIdSelect();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * 
	 */
	public List<EmployeeOfDepartment> getListEmployeeOfDepartment(int department_id) {
		try {
			return departmentMapper.sqlGetListEmployeeOfDepartmentByStatus(department_id, ROLE_EMPLOYEE, STATUS_STAY,
					ACTIVE, ACTIVE, ACTION_DELETE);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/**
	 * 
	 */
	public List<EmployeeOfDepartment> getListEmployeeNotInDepartment(int department_id) {
		try {
			return departmentMapper.sqlGetListEmployeeOfDepartmentByStatus(department_id, ROLE_EMPLOYEE, STATUS_LEAVE,
					ACTIVE, ACTIVE, ACTION_ADD);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
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
		HttpServletRequest request = null;
		HttpSession session = request.getSession(false);
		try {
			// Check duplicate Department Name
			if (departmentMapper.sqlCountDepartmentByNameSelect(department.getDepartmentName()) == 0) {
				// Check duplicate Department Code
				if (departmentMapper.sqlCountDepartmentByCodeSelect(department.getDepartmentCode()) == 0) {
					department.setNumberOfEmployees(NUMBER_OF_EMPLOYEE_ZERO);
					department.setIsActivated(ACTIVE);
					department.setUpdateInfo(new UpdateInfo(session.getAttribute("usernameLogin"),));
					return departmentMapper.sqlDepartmentInsert(department);
				} else {
					return -2;
				}
			} else {
				return -1;
			}
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
