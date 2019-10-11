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
		return new ResponseEntity<List<Department>>(departmentService.getAllListDepartment(), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/getListDepartmentActive", method = RequestMethod.GET)
	public ResponseEntity<List<Department>> getListDepartmentActive() {
		return new ResponseEntity<List<Department>>(departmentService.getListDepartmentActive(), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/getDepartmentById/{departmentId}", method = RequestMethod.GET)
	public ResponseEntity<Department> getDepartmentById(@PathVariable int departmentId) {
		return new ResponseEntity<Department>(departmentService.getDepartmentById(departmentId), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/getListEmployeeOfDepartment/{departmentId}", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeOfDepartment>> getListEmployeeOfDepartment(@PathVariable int departmentId) {
		return new ResponseEntity<List<EmployeeOfDepartment>>(
				departmentService.getListEmployeeOfDepartment(departmentId), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/getListEmployeeNotInDepartment/{departmentId}", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeOfDepartment>> getListEmployeeNotInDepartment(@PathVariable int departmentId) {
		return new ResponseEntity<List<EmployeeOfDepartment>>(
				departmentService.getListEmployeeNotInDepartment(departmentId), HttpStatus.OK);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/updateDepartmentInfomation", method = RequestMethod.POST)
	public ResponseEntity<String> updateDepartmentInfomation(@RequestBody Department department) {
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
	public ResponseEntity<String> activeDepartment(@RequestBody Department department) {
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
	public ResponseEntity<String> inActiveDepartment(@RequestBody Department department) {
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
	public ResponseEntity<String> insertDepartment(@RequestBody Department department) {
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
	public ResponseEntity<String> addNewEmployeeToDepartment(@RequestBody EmployeeOfDepartment employeeOfDepartment) {
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
	public ResponseEntity<String> removeEmployeeFromDepartment(@RequestBody EmployeeOfDepartment employeeOfDepartment) {
		if (departmentService.removeEmployeeFromDepartment(employeeOfDepartment.getDepartmentDetailId()) != 0) {
			return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Failed!!!", HttpStatus.BAD_REQUEST);
		}
	}

}
