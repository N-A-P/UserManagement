package com.mockproject.du1.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.du1.model.Role;
import com.mockproject.du1.services.RoleService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/rest")
public class RoleRestController {
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
	private RoleService roleService;

	/**
	 * Click button show list role
	 */
	@RequestMapping(value = "/getAllListRole", method = RequestMethod.GET)
	public ResponseEntity<List<Role>> getAllListRole() {
		List<Role> roles = roleService.getAllListRole();
		try {
			if (roles != null && !roles.isEmpty()) {
				return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
			}

		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
		}
		return null;
	}

	/**
	 * Search Role by Name or Code
	 */
	@RequestMapping(value = "/getRoleByNameOrCodeSelect/{extractCondition}", method = RequestMethod.GET)
	public ResponseEntity<List<Role>> getRoleByNameOrCodeSelect(@Valid @PathVariable String extractCondition) {
		List<Role> roles = roleService.getRoleByNameOrCodeSelect(extractCondition);
		try {
			if (roles != null && !roles.isEmpty()) {
				return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
			}

		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
		}
		return null;
	}

	@RequestMapping(value = "/getRoleById/{roleId}", method = RequestMethod.GET)
	public ResponseEntity<Role> getRoleById(@PathVariable int roleId) {
		Role role = roleService.getRoleById(roleId);
		try {
			if (role != null) {
				return new ResponseEntity<Role>(role, HttpStatus.OK);
			}

		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
		}

		return null;
	}

	@RequestMapping(value = "/updateRoleInfomation", method = RequestMethod.POST)
	public ResponseEntity<String> updateRoleInfomation(@Valid @RequestBody Role role) {

		try {
			int checkUpdateSuccess = roleService.editRoleInfoUpdate(role);

			if (checkUpdateSuccess == CONSTANT_CHECK_DUPLICATED_CODE) {
				return new ResponseEntity<String>("Duplicated Role code!!! Please Check!!!", HttpStatus.BAD_REQUEST);

			} else if (checkUpdateSuccess == CONSTANT_CHECK_DUPLICATED_NAME) {
				return new ResponseEntity<String>("Duplicated Role name!!! Please Check!!!", HttpStatus.BAD_REQUEST);

			} else if (checkUpdateSuccess == 0) {
				return new ResponseEntity<String>("Database rollback!!! Modify Role failed!!!", HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
			}

		} catch (Exception e) {
			return new ResponseEntity<String>("SQL Error Code: " + ((SQLException) e).getErrorCode(), HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 *
	 */
	@RequestMapping(value = "/insertRole", method = RequestMethod.POST)
	public ResponseEntity<String> insertRole(@Valid @RequestBody Role role) {

		try {

			int checkInsertSuccess = roleService.addNewRole(role);

			if (checkInsertSuccess == CONSTANT_CHECK_DUPLICATED_CODE) {
				return new ResponseEntity<String>("Duplicated Role code!!! Please Check!!!", HttpStatus.BAD_REQUEST);

			} else if (checkInsertSuccess == CONSTANT_CHECK_DUPLICATED_NAME) {
				return new ResponseEntity<String>("Duplicated Role name!!! Please Check!!!", HttpStatus.BAD_REQUEST);

			} else if (checkInsertSuccess == 0) {
				return new ResponseEntity<String>("Database rollback!!! Adding new Role failed!!!",
						HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Success!!!", HttpStatus.CREATED);
			}
		} catch (DuplicateKeyException e) {
			return new ResponseEntity<String>("Duplicated Key!!! Database rollback!!! Adding new Role failed!!!",
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<String>("SQL Error Code: " + ((SQLException) e).getErrorCode(), HttpStatus.BAD_REQUEST);
		}

	}

}
