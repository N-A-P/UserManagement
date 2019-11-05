package com.mockproject.du1.services;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.mockproject.du1.mapper.DepartmentMapper;
import com.mockproject.du1.model.Department;
import com.mockproject.du1.model.EmployeeOfDepartment;
import com.mockproject.du1.model.EmployeeOfDepartmentResponse;
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
	public List<EmployeeOfDepartment> getListEmployeeOfDepartment(long department_id) {
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
	public List<EmployeeOfDepartment> getListEmployeeNotInDepartment(long department_id) {
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

	public Map<Integer, List<Department>> departmentInfoUpdate(Department department) throws SQLException {
		Map<Integer, List<Department>> mapDepartment = new HashMap<>();

		try {
			// Check duplicate Department Name
			if (departmentMapper.sqlCountDepartmentByNameSelect(department.getName()) == 0) {
				// Check duplicate Department Code
				if (departmentMapper.sqlCountDepartmentByCodeSelect(department.getCode()) == 0) {

					// set value for field public.department.updated_by
					department.setUpdatedBy(usernameLogin);
					// set value for field public.department.created_timestamp
					department.setCreatedTimestamp(currentTimestamp);
					// set value for field public.department.updated_timestamp
					department.setUpdatedTimestamp(currentTimestamp);

					// return result update database query
					int checkUpdate = departmentMapper.sqlDepartmentInfoUpdate(department);
					if (checkUpdate != 0) {
						mapDepartment.put(checkUpdate, departmentMapper.sqlGetAllDepartmentSelect());
						return mapDepartment;
					}

				} else {

					mapDepartment.put(CONSTANT_CHECK_DUPLICATED_CODE, null);
					return mapDepartment;
				}
			} else {
				mapDepartment.put(CONSTANT_CHECK_DUPLICATED_NAME, null);
				return mapDepartment;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			mapDepartment.put(0, null);
		}

		return mapDepartment;
	}

	/**
	 * 
	 */

	public Map<Integer, List<Department>> departmentInsert(Department department)
			throws DuplicateKeyException, SQLException {

		Map<Integer, List<Department>> mapDepartment = new HashMap<Integer, List<Department>>();

		try {
			// Check duplicate Department Name
			if (departmentMapper.sqlCountDepartmentByNameSelect(department.getName()) == 0) {
				// Check duplicate Department Code
				if (departmentMapper.sqlCountDepartmentByCodeSelect(department.getCode()) == 0) {

					// set value for field public.department.number_of_employee
					department.setNumberOfEmployee(NUMBER_OF_EMPLOYEE_ZERO);
					// set value for field public.department.is_activated
					department.setIsActivated(ACTIVE);
					// set value for field public.department.updated_by
					department.setUpdatedBy(usernameLogin);
					// set value for field public.department.created_timestamp
					department.setCreatedTimestamp(currentTimestamp);
					// set value for field public.department.updated_timestamp
					department.setUpdatedTimestamp(currentTimestamp);

					// return result insert database query
					int checkInsert = departmentMapper.sqlDepartmentInsert(department);
					if (checkInsert != 0) {
						mapDepartment.put(checkInsert, departmentMapper.sqlGetAllDepartmentSelect());
						return mapDepartment;
					}

				} else {
					mapDepartment.put(CONSTANT_CHECK_DUPLICATED_CODE, null);
					return mapDepartment;
				}
			} else {
				mapDepartment.put(CONSTANT_CHECK_DUPLICATED_NAME, null);
				return mapDepartment;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		mapDepartment.put(0, null);
		return mapDepartment;
	}

	/**
	 * 
	 */
	public List<Department> activeDepartment(long departmentId) throws SQLException {

		int checkUpdate = 0;
		Department department = new Department();
		try {

			// return result update database query
			department.setId(departmentId);
			department.setIsActivated(ACTIVE);
			department.setUpdatedBy(usernameLogin);
			department.setUpdatedTimestamp(currentTimestamp);
			checkUpdate = departmentMapper.sqlDepartmentStatusUpdate(department);
			if (checkUpdate != 0) {
				return departmentMapper.sqlGetAllDepartmentSelect();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

	/**
	 * 
	 */
	public List<Department> inActiveDepartment(long departmentId) throws SQLException {

		int checkUpdate = 0;
		Department department = new Department();
		try {
			department.setId(departmentId);
			department.setIsActivated(INACTIVE);
			department.setUpdatedBy(usernameLogin);
			department.setUpdatedTimestamp(currentTimestamp);
			// return result update database query
			checkUpdate = departmentMapper.sqlDepartmentStatusUpdate(department);
			if (checkUpdate != 0) {
				return departmentMapper.sqlGetAllDepartmentSelect();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return departmentMapper.sqlGetAllDepartmentSelect();

	}

	public EmployeeOfDepartmentResponse removeEmployeeFromDeparment(
			List<EmployeeOfDepartment> listEmployeeOfDepartment) {
		List<EmployeeOfDepartment> listRecordError = new ArrayList<EmployeeOfDepartment>();
		List<EmployeeOfDepartment> employeeOfDepartmentsReturn = new ArrayList<EmployeeOfDepartment>();
		EmployeeOfDepartmentResponse employeeOfDepartmentResponse = new EmployeeOfDepartmentResponse();
		UserDepartment userDepartment = new UserDepartment();
		Department department = new Department();

		try {
			for (EmployeeOfDepartment employeeOfDepartment : listEmployeeOfDepartment) {

				try {

					userDepartment.setUserId(employeeOfDepartment.getUserId());
					userDepartment.setDepartmentId(employeeOfDepartment.getDepartments().get(0).getId());
					userDepartment.setLeaveDate(currentTimestamp);
					userDepartment.setStayOrLeave(STATUS_STAY);
					userDepartment.setUpdatedBy(usernameLogin);
					userDepartment.setUpdatedTimestamp(currentTimestamp);

					if (departmentMapper.sqlCheckExistUserDepartmentSelect(userDepartment) != 0) {

						int checkUpdate = departmentMapper.sqlRemoveEmployeeForDeparmentUpdate(userDepartment,
								STATUS_LEAVE);

						if (checkUpdate == 0) {

							listRecordError.add(employeeOfDepartment);

						} else {

							department.setId(employeeOfDepartment.getDepartments().get(0).getId());
							department.setNumberOfEmployee(checkUpdate);
							department.setUpdatedBy(usernameLogin);
							department.setUpdatedTimestamp(currentTimestamp);

							departmentMapper.sqlDepartmentNumberOfEmployeeUpdate(department, ACTION_DELETE);
							employeeOfDepartmentsReturn = getListEmployeeOfDepartment(
									employeeOfDepartment.getDepartments().get(0).getId());

						}
					} else {
						listRecordError.add(employeeOfDepartment);
					}

				} catch (DuplicateKeyException e) {
					listRecordError.add(employeeOfDepartment);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		employeeOfDepartmentResponse.setEmployeeOfDepartmentsError(listRecordError);
		employeeOfDepartmentResponse.setEmployeeOfDepartmentsReturn(employeeOfDepartmentsReturn);
		return employeeOfDepartmentResponse;
	}

	/**
	 * 
	 */
	public EmployeeOfDepartmentResponse newEmployeeForDeparmentInsert(
			List<EmployeeOfDepartment> listEmployeeOfDepartment) {
		List<EmployeeOfDepartment> listRecordError = new ArrayList<EmployeeOfDepartment>();
		List<EmployeeOfDepartment> employeeOfDepartmentsReturn = new ArrayList<EmployeeOfDepartment>();
		EmployeeOfDepartmentResponse employeeOfDepartmentResponse = new EmployeeOfDepartmentResponse();
		UserDepartment userDepartment = new UserDepartment();
		Department department = new Department();
		try {
			for (EmployeeOfDepartment employeeOfDepartment : listEmployeeOfDepartment) {

				try {
					userDepartment.setUserId(employeeOfDepartment.getUserId());
					userDepartment.setDepartmentId(employeeOfDepartment.getDepartments().get(0).getId());
					userDepartment.setJoinDate(currentTimestamp);
					userDepartment.setLeaveDate(null);
					userDepartment.setStayOrLeave(STATUS_STAY);
					userDepartment.setUpdatedBy(usernameLogin);
					userDepartment.setCreatedTimestamp(currentTimestamp);
					userDepartment.setUpdatedTimestamp(currentTimestamp);

					if (departmentMapper.sqlCheckExistUserDepartmentSelect(userDepartment) == 0) {

						int checkInsert = departmentMapper.sqlNewEmployeeForDeparmentInsert(userDepartment);

						if (checkInsert == 0) {

							listRecordError.add(employeeOfDepartment);

						} else {

							department.setId(employeeOfDepartment.getDepartments().get(0).getId());
							department.setNumberOfEmployee(checkInsert);
							department.setUpdatedBy(usernameLogin);
							department.setUpdatedTimestamp(currentTimestamp);

							departmentMapper.sqlDepartmentNumberOfEmployeeUpdate(department, ACTION_ADD);
							employeeOfDepartmentsReturn = getListEmployeeNotInDepartment(
									employeeOfDepartment.getDepartments().get(0).getId());

						}
					} else {
						listRecordError.add(employeeOfDepartment);
					}

				} catch (DuplicateKeyException e) {
					listRecordError.add(employeeOfDepartment);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		employeeOfDepartmentResponse.setEmployeeOfDepartmentsError(listRecordError);
		employeeOfDepartmentResponse.setEmployeeOfDepartmentsReturn(employeeOfDepartmentsReturn);
		return employeeOfDepartmentResponse;
	}

}
