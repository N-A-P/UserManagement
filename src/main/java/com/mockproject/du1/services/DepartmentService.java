package com.mockproject.du1.services;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.mockproject.du1.common.DataUtil;
import com.mockproject.du1.mapper.DepartmentMapper;
import com.mockproject.du1.model.Department;
import com.mockproject.du1.model.EmployeeOfDepartment;
import com.mockproject.du1.model.UserDepartment;

@Service
public class DepartmentService {
	HttpServletRequest request = null;
	String usernameLogin;
	String currentTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
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

		try {
			HttpSession session = request.getSession(false);
			usernameLogin = (String) session.getAttribute("usernameLogin");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		try {
			// Check duplicate Department Name
			if (departmentMapper.sqlCountDepartmentByNameSelect(department.getDepartmentName()) == 0) {
				// Check duplicate Department Code
				if (departmentMapper.sqlCountDepartmentByCodeSelect(department.getDepartmentCode()) == 0) {

					// set value for field public.department.updated_by
					department.setUpdateBy(usernameLogin);
					// set value for field public.department.created_timestamp
					department.setCreateTimestamp(currentTimestamp);
					// set value for field public.department.updated_timestamp
					department.setUpdateTimestamp(currentTimestamp);

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

		return departmentMapper.sqlDepartmentStatusUpdate(departmentId, ACTIVE, usernameLogin, currentTimestamp);
	}

	/**
	 * 
	 */
	public int inActiveDepartment(int departmentId) throws SQLException {

		return departmentMapper.sqlDepartmentStatusUpdate(departmentId, INACTIVE, usernameLogin, currentTimestamp);
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
	public int removeEmployeeFromDepartment(EmployeeOfDepartment employeeOfDepartment) {
		try {
			UserDepartment userDepartment = new UserDepartment();
			userDepartment.setUserDepartmentId(employeeOfDepartment.getUserDepartmentId());
			userDepartment.setLeaveDate(currentTimestamp);
			userDepartment.setStayOrLeave(STATUS_LEAVE);
			userDepartment.setUpdateBy(usernameLogin);
			userDepartment.setUpdateTimestamp(currentTimestamp);
			return departmentMapper.sqlRemoveEmployeeForDeparmentUpdate(userDepartment);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return 0;
	}

	/**
	 * 
	 */
	public int departmentInsert(Department department) throws DuplicateKeyException, SQLException {
		try {
//			HttpSession session = request.getSession(false);
//			usernameLogin = (String) session.getAttribute("usernameLogin");
		} catch (Exception e) {
		}

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
					department.setCreateTimestamp(currentTimestamp);
					// set value for field public.department.updated_timestamp
					department.setUpdateTimestamp(currentTimestamp);

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
		UserDepartment userDepartment = new UserDepartment();
		try {

			for (EmployeeOfDepartment employeeOfDepartment : listEmployeeOfDepartment) {
				try {
					userDepartment.setUserId(employeeOfDepartment.getUserId());
					userDepartment.setDepartmentId(employeeOfDepartment.getDepartmentId());
					userDepartment.setJoinDate(currentTimestamp);
					userDepartment.setLeaveDate(null);
					userDepartment.setStayOrLeave(STATUS_STAY);
					userDepartment.setUpdateBy(usernameLogin);
					userDepartment.setCreateTimestamp(currentTimestamp);
					userDepartment.setUpdateTimestamp(currentTimestamp);
					if (departmentMapper.sqlNewEmployeeForDeparmentInsert(userDepartment) == 0) {
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
