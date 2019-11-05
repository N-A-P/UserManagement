package com.mockproject.du1.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mockproject.du1.model.Department;
import com.mockproject.du1.model.EmployeeOfDepartment;
import com.mockproject.du1.model.EmployeeOfDepartmentResponse;
import com.mockproject.du1.services.DepartmentService;

import javax.validation.Valid;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/rest")
public class DepartmentRestController {

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
	private DepartmentService departmentService;

	/**
	 * Click button show list department(Active & Inactive)
	 */
	@GetMapping(value = "/getAllListDepartment")
	public ResponseEntity<List<Department>> getAllListDepartment() {
		List<Department> departments = departmentService.getAllListDepartment();
		try {
			if (departments != null && !departments.isEmpty()) {
				return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
			}

		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
		}
		return null;
	}

	/**
	 *
	 */
	@GetMapping(value = "/getListDepartmentActive")
	public ResponseEntity<List<Department>> getListDepartmentActive() {
		List<Department> departments = departmentService.getListDepartmentActive();
		try {
			if (departments != null && !departments.isEmpty()) {
				return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
			}

		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
		}
		return null;
	}

	/**
	 * <<<<<<< HEAD Show list department(Active) =======
	 * <p>
	 * >>>>>>> branch 'master' of https://github.com/ntgptit/UserManagement
	 */
	@GetMapping(value = "/getDepartmentById/{departmentId}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable int departmentId) {
		Department department = departmentService.getDepartmentById(departmentId);
		try {
			if (department != null) {
				return new ResponseEntity<Department>(department, HttpStatus.OK);
			}

		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
		}

		return null;
	}

	/**
	 *
	 */
	@GetMapping(value = "/getListEmployeeOfDepartment/{departmentId}")
	public ResponseEntity<List<EmployeeOfDepartment>> getListEmployeeOfDepartment(
			@PathVariable @Valid int departmentId) {
		List<EmployeeOfDepartment> departments = departmentService.getListEmployeeOfDepartment(departmentId);
		try {
			if (departments != null && !departments.isEmpty()) {
				return new ResponseEntity<List<EmployeeOfDepartment>>(departments, HttpStatus.OK);

			}

		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
		}
		return null;
	}

	/**
	 *
	 */
	@GetMapping(value = "/getListEmployeeNotInDepartment/{departmentId}")
	public ResponseEntity<List<EmployeeOfDepartment>> getListEmployeeNotInDepartment(@PathVariable int id) {
		List<EmployeeOfDepartment> departments = departmentService.getListEmployeeNotInDepartment(id);
		try {
			if (departments != null && !departments.isEmpty()) {
				return new ResponseEntity<List<EmployeeOfDepartment>>(departments, HttpStatus.OK);
			}
		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
		}
		return null;
	}

	/**
	 * <<<<<<< HEAD Click button Save =======
	 * <p>
	 * >>>>>>> branch 'master' of https://github.com/ntgptit/UserManagement
	 */
	@PostMapping(value = "/updateDepartmentInfomation")
	public ResponseEntity<Map<String, List<Department>>> updateDepartmentInfomation(
			@Valid @RequestBody Department department) {
		Map<String, List<Department>> mapDepartmentReturn = new HashMap<String, List<Department>>();
		try {
			Map<Integer, List<Department>> mapDepartment = departmentService.departmentInfoUpdate(department);

			for (Map.Entry<Integer, List<Department>> entry : mapDepartment.entrySet()) {

				if (entry.getKey() == CONSTANT_CHECK_DUPLICATED_CODE) {

					mapDepartmentReturn.put("Duplicated Department code!!! Please Check!!!", entry.getValue());

					return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn,
							HttpStatus.BAD_REQUEST);
				} else if (entry.getKey() == CONSTANT_CHECK_DUPLICATED_NAME) {
					mapDepartmentReturn.put("Duplicated Department name!!! Please Check!!!", entry.getValue());
					return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn,
							HttpStatus.BAD_REQUEST);
				} else if (entry.getKey() == 0) {

					mapDepartmentReturn.put("Database rollback!!! Modify department failed!!!", entry.getValue());
					return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn,
							HttpStatus.BAD_REQUEST);
				} else {
					mapDepartmentReturn.put("Success!!!", entry.getValue());
					return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn, HttpStatus.CREATED);
				}
			}
		} catch (SQLException e) {
			mapDepartmentReturn.put("SQL Error Code: " + e.getErrorCode(), null);
		}
		return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn, HttpStatus.BAD_REQUEST);
	}

	/**
	 *
	 */
	@PostMapping(value = "/activeDepartment")
	public ResponseEntity<Map<String, List<Department>>> activeDepartment(@Valid @RequestBody Department department) {
		Map<String, List<Department>> mapDepartmentReturn = new HashMap<String, List<Department>>();

		try {
			List<Department> departments = departmentService.activeDepartment(department.getId());
			if (departments != null) {
				mapDepartmentReturn.put("Success!!!", departments);
				return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn, HttpStatus.CREATED);
			} else {
				mapDepartmentReturn.put("Failed!!!", null);
				return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn, HttpStatus.BAD_REQUEST);
			}
		} catch (SQLException e) {
			mapDepartmentReturn.put("SQL Error Code: " + e.getErrorCode(), null);
		}

		return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn, HttpStatus.BAD_REQUEST);

	}

	/**
	 *
	 */
	@PostMapping(value = "/inActiveDepartment")
	public ResponseEntity<Map<String, List<Department>>> inActiveDepartment(@Valid @RequestBody Department department) {

		Map<String, List<Department>> mapDepartmentReturn = new HashMap<String, List<Department>>();

		try {
			List<Department> departments = departmentService.inActiveDepartment(department.getId());
			if (departments != null) {
				mapDepartmentReturn.put("Success!!!", departments);
				return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn, HttpStatus.CREATED);
			} else {
				mapDepartmentReturn.put("Failed!!!", null);
				return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn, HttpStatus.BAD_REQUEST);
			}
		} catch (SQLException e) {
			mapDepartmentReturn.put("SQL Error Code: " + e.getErrorCode(), null);
		}

		return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn, HttpStatus.BAD_REQUEST);
	}

	/**
	 *
	 */
	@PostMapping(value = "/insertDepartment")
	public ResponseEntity<Map<String, List<Department>>> insertDepartment(@Valid @RequestBody Department department) {

		Map<String, List<Department>> mapDepartmentReturn = new HashMap<String, List<Department>>();
		try {
			Map<Integer, List<Department>> mapDepartment = departmentService.departmentInsert(department);

			for (Map.Entry<Integer, List<Department>> entry : mapDepartment.entrySet()) {

				if (entry.getKey() == CONSTANT_CHECK_DUPLICATED_CODE) {

					mapDepartmentReturn.put("Duplicated Department code!!! Please Check!!!", entry.getValue());

					return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn,
							HttpStatus.BAD_REQUEST);
				} else if (entry.getKey() == CONSTANT_CHECK_DUPLICATED_NAME) {
					mapDepartmentReturn.put("Duplicated Department name!!! Please Check!!!", entry.getValue());
					return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn,
							HttpStatus.BAD_REQUEST);
				} else if (entry.getKey() == 0) {

					mapDepartmentReturn.put("Database rollback!!! Add new department failed!!!", entry.getValue());
					return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn,
							HttpStatus.BAD_REQUEST);
				} else {
					mapDepartmentReturn.put("Success!!!", entry.getValue());
					return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn, HttpStatus.CREATED);
				}
			}
		} catch (SQLException e) {
			mapDepartmentReturn.put("SQL Error Code: " + e.getErrorCode(), null);
		}
		return new ResponseEntity<Map<String, List<Department>>>(mapDepartmentReturn, HttpStatus.BAD_REQUEST);

	}

	/**
	 *
	 */
	@PostMapping(value = "/addNewEmployeeToDepartment")
	public ResponseEntity<EmployeeOfDepartmentResponse> addNewEmployeeToDepartment(
			@Valid @RequestBody List<EmployeeOfDepartment> listEmployeeNotInDepartment) {
		EmployeeOfDepartmentResponse employeeOfDepartmentResponse = departmentService
				.newEmployeeForDeparmentInsert(listEmployeeNotInDepartment);

		if (employeeOfDepartmentResponse.getEmployeeOfDepartmentsError().isEmpty()) {
			return new ResponseEntity<EmployeeOfDepartmentResponse>(employeeOfDepartmentResponse, HttpStatus.CREATED);
		} else if (!employeeOfDepartmentResponse.getEmployeeOfDepartmentsError().isEmpty()
				&& employeeOfDepartmentResponse.getEmployeeOfDepartmentsError().size() < listEmployeeNotInDepartment
						.size()) {
			return new ResponseEntity<EmployeeOfDepartmentResponse>(employeeOfDepartmentResponse, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<EmployeeOfDepartmentResponse>(employeeOfDepartmentResponse,
					HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 *
	 */
	@PostMapping(value = "/removeEmployeeFromDepartment")
	public ResponseEntity<EmployeeOfDepartmentResponse> removeEmployeeFromDepartment(
			@Valid @RequestBody List<EmployeeOfDepartment> listEmployeeInDepartment) {

		EmployeeOfDepartmentResponse employeeOfDepartmentResponse = departmentService
				.removeEmployeeFromDeparment(listEmployeeInDepartment);
		List<EmployeeOfDepartment> listError = employeeOfDepartmentResponse.getEmployeeOfDepartmentsError();

		if (listError.isEmpty()) {
			return new ResponseEntity<EmployeeOfDepartmentResponse>(employeeOfDepartmentResponse, HttpStatus.CREATED);
		} else if (!listError.isEmpty() && listError.size() < listEmployeeInDepartment.size()) {
			return new ResponseEntity<EmployeeOfDepartmentResponse>(employeeOfDepartmentResponse, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<EmployeeOfDepartmentResponse>(employeeOfDepartmentResponse,
					HttpStatus.BAD_REQUEST);
		}
	}

}
