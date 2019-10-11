package com.mockproject.du1.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mockproject.du1.model.Department;
import com.mockproject.du1.model.EmployeeOfDepartment;
import com.mockproject.du1.services.DepartmentService;
import com.mockproject.du1.services.EmailService;

import javax.validation.Valid;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/rest")
public class DepartmentRestController {

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	EmailService emailService;


	@RequestMapping(value = "/getAllListDepartment", method = RequestMethod.GET)
	public ResponseEntity<List<Department>> getAllListDepartment() {
		List<Department> departments = departmentService.getAllListDepartment();
		try {
			if (departments != null && !departments.isEmpty()) {
				return new ResponseEntity<List<Department>>(departmentService.getAllListDepartment(), HttpStatus.OK);
			}

		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
		}
		return null;
	}

	/**
	 *
	 */
	@RequestMapping(value = "/getListDepartmentActive", method = RequestMethod.GET)
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
	 *
	 */
	@RequestMapping(value = "/getDepartmentById/{departmentId}", method = RequestMethod.GET)
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
	@RequestMapping(value = "/getListEmployeeOfDepartment/{departmentId}", method = RequestMethod.GET)
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
	@RequestMapping(value = "/getListEmployeeNotInDepartment/{departmentId}", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeOfDepartment>> getListEmployeeNotInDepartment(@PathVariable int departmentId) {
		List<EmployeeOfDepartment> departments = departmentService.getListEmployeeNotInDepartment(departmentId);
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
	@RequestMapping(value = "/updateDepartmentInfomation", method = RequestMethod.POST)
	public ResponseEntity<String> updateDepartmentInfomation(@Valid @RequestBody Department department) {
		if (departmentService.departmentInfoUpdate(department) != 0) {
			return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Failed!!!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *
	 */
	@RequestMapping(value = "/activeDepartment", method = RequestMethod.POST)
	public ResponseEntity<String> activeDepartment(@Valid @RequestBody Department department) {
		if (departmentService.activeDepartment(department.getDepartmentId()) != 0) {
			return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Failed!!!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *
	 */
	@RequestMapping(value = "/inActiveDepartment", method = RequestMethod.POST)
	public ResponseEntity<String> inActiveDepartment(@Valid @RequestBody Department department) {
		if (departmentService.inActiveDepartment(department.getDepartmentId()) != 0) {
			return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Failed!!!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *
	 */
	@RequestMapping(value = "/insertDepartment", method = RequestMethod.POST)
	public ResponseEntity<String> insertDepartment(@Valid @RequestBody Department department) {
		if (departmentService.departmentInsert(department) != 0) {
			return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Failed!!!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *
	 */
	@RequestMapping(value = "/addNewEmployeeToDepartment", method = RequestMethod.POST)
	public ResponseEntity<String> addNewEmployeeToDepartment(
			@Valid @RequestBody EmployeeOfDepartment employeeOfDepartment) {
		if (departmentService.newEmployeeForDeparmentInsert(employeeOfDepartment) != 0) {

			return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Failed!!!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *
	 */
	@RequestMapping(value = "/removeEmployeeFromDepartment", method = RequestMethod.POST)
	public ResponseEntity<String> removeEmployeeFromDepartment(
			@Valid @RequestBody EmployeeOfDepartment employeeOfDepartment) {
		if (departmentService.removeEmployeeFromDepartment(employeeOfDepartment.getDepartmentDetailId()) != 0) {
			return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Failed!!!", HttpStatus.BAD_REQUEST);
		}
	}
}
