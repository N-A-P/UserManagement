package com.mockproject.du1.services;

import java.io.IOException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mockito.internal.stubbing.answers.ThrowsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.support.SQLErrorCodes;
import org.springframework.stereotype.Service;

import com.mockproject.du1.common.DataUtil;
import com.mockproject.du1.mapper.DepartmentMapper;
import com.mockproject.du1.model.Department;
import com.mockproject.du1.model.EmployeeOfDepartment;
import com.mockproject.du1.model.UpdateInfo;

@Service
public class DepartmentService {
	HttpServletRequest request = null;
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
	private static final int CONSTANT_CHECK_DUPLICATED_CODE = -2;
	/**
	 * 
	 */
	private static final int CONSTANT_CHECK_DUPLICATED_NAME = -1;
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
			return departmentMapper.sqlGetListEmployeeOfDepartmentByStatus(department_id, STATUS_STAY, ACTIVE, ACTIVE,
					ACTION_DELETE);
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
			return departmentMapper.sqlGetListEmployeeOfDepartmentByStatus(department_id, STATUS_LEAVE, ACTIVE, ACTIVE,
					ACTION_ADD);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/** 
	 * 
	 */
	public int departmentInfoUpdate(Department department) throws SQLException {

		HttpSession session = request.getSession(false);
		String usernameLogin = (String) session.getAttribute("usernameLogin");

		try {
			// Check duplicate Department Name
			if (departmentMapper.sqlCountDepartmentByNameSelect(department.getDepartmentName()) == 0) {
				// Check duplicate Department Code
				if (departmentMapper.sqlCountDepartmentByCodeSelect(department.getDepartmentCode()) == 0) {

					// set value for field public.department.updated_by
					department.setUpdateBy(usernameLogin);
					// set value for field public.department.created_timestamp
					department.setCreateTimestamp(DataUtil.getCurrentTimestamp().toString());
					// set value for field public.department.updated_timestamp
					department.setUpdateTimestamp(DataUtil.getCurrentTimestamp().toString());

					// return result update database query
					return departmentMapper.sqlDepartmentInfoUpdate(department);

				} else {
					return CONSTANT_CHECK_DUPLICATED_CODE;
				}
			} else {
				return CONSTANT_CHECK_DUPLICATED_NAME;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * 
	 */
	public int activeDepartment(int departmentId) throws SQLException {

		return departmentMapper.sqlDepartmentStatusUpdate(departmentId, ACTIVE);
	}

	/**
	 * 
	 */
	public int inActiveDepartment(int departmentId) throws SQLException {

		return departmentMapper.sqlDepartmentStatusUpdate(departmentId, INACTIVE);
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
	public int departmentInsert(Department department) throws DuplicateKeyException, SQLException {

		HttpSession session = request.getSession(false);
		String usernameLogin = (String) session.getAttribute("usernameLogin");
		try {
			// Check duplicate Department Name
			if (departmentMapper.sqlCountDepartmentByNameSelect(department.getDepartmentName()) == 0) {
				// Check duplicate Department Code
				if (departmentMapper.sqlCountDepartmentByCodeSelect(department.getDepartmentCode()) == 0) {

					// set value for field public.department.number_of_employee
					department.setNumberOfEmployees(NUMBER_OF_EMPLOYEE_ZERO);
					// set value for field public.department.is_activated
					department.setIsActivated(ACTIVE);
					// set value for field public.department.updated_by
					department.setUpdateBy(usernameLogin);
					// set value for field public.department.created_timestamp
					department.setCreateTimestamp(DataUtil.getCurrentTimestamp().toString());
					// set value for field public.department.updated_timestamp
					department.setUpdateTimestamp(DataUtil.getCurrentTimestamp().toString());

					// return result insert database query
					return departmentMapper.sqlDepartmentInsert(department);

				} else {
					return CONSTANT_CHECK_DUPLICATED_CODE;
				}
			} else {
				return CONSTANT_CHECK_DUPLICATED_NAME;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * 
	 */
	public List<EmployeeOfDepartment> newEmployeeForDeparmentInsert(
			List<EmployeeOfDepartment> listEmployeeOfDepartment) {
		List<EmployeeOfDepartment> listRecordError = new ArrayList<EmployeeOfDepartment>();
		try {

			for (EmployeeOfDepartment employeeOfDepartment : listEmployeeOfDepartment) {
				try {
					if (departmentMapper.sqlNewEmployeeForDeparmentInsert(employeeOfDepartment, STATUS_STAY) == 0) {
						listRecordError.add(employeeOfDepartment);
					}
				} catch (DuplicateKeyException e) {
					listRecordError.add(employeeOfDepartment);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return listRecordError;
	}
}
