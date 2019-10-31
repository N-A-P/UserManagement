package com.mockproject.du1.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.du1.exception.CustomException;
import com.mockproject.du1.model.Users;
import com.mockproject.du1.model.UsersFull;
import com.mockproject.du1.services.EmailService;
import com.mockproject.du1.services.JwtService;
import com.mockproject.du1.services.UsersService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/rest")
public class UsersRestController {
	/**
	 *
	 */
	@Autowired
	private JwtService jwtService;
	/**
	 *
	 */
	@Autowired
	private UsersService usersService;
	/**
	 *
	 */
	@Autowired
	private EmailService emailService;
	/**
	 *
	 */
	Logger log = LoggerFactory.getLogger(UsersRestController.class);

	/* ---------------- GET ALL USER LIST ------------------------ */
	@RequestMapping(value = "/users/list", method = RequestMethod.GET)
	public ResponseEntity<List<UsersFull>> getAllUser() {
		return new ResponseEntity<List<UsersFull>>(usersService.getAllUserFull(null), HttpStatus.OK);
	}

	/* ---------------- GET ACTIVE/INACTIVE USER LIST ------------------------ */
	@RequestMapping(value = "/users/list/{isActivated}", method = RequestMethod.GET)
	public ResponseEntity<List<UsersFull>> getAllUser(@PathVariable int isActivated) {
		return new ResponseEntity<List<UsersFull>>(usersService.getAllUserFull(isActivated), HttpStatus.OK);
	}


	/* ---------------- REGISTRATION NEW USER ------------------------ */
	@RequestMapping(value = {"/register","/users/add"}, method = RequestMethod.POST)
	public ResponseEntity<String> registerNewCustomer(@RequestBody Users user) {
		try {
			usersService.registerNewCustomer(user);
			return new ResponseEntity<String>("Created!", HttpStatus.OK);
		} catch (CustomException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.message, HttpStatus.BAD_REQUEST);
		}
	}

	/* ---------------- SEND EMAIL TO LIST OF USERS ------------------------ */
	// @RequestMapping(value = "/email", method = RequestMethod.POST)
	// public ResponseEntity<List<Users>> coverExcel(@RequestBody File file) throws
	// IOException {
	// List<String> emails = emailService.coverExcellFileToArray(file);
	// List<Users> users = new ArrayList<>();
	// users = usersService.getUsersListByEmails(emails);
	// return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
	// }

	/* ---------------- LOGIN ------------------------ */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(HttpServletRequest request, @RequestBody Users user) {
		String message;
		HttpStatus status = null;
		if (null == user.getUsername() || null == user.getPassword()) {
			message = "Enter the username and password! ";
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<String>(message, status);
		}
		Users usertmp = usersService.getUserLogin(user.getUsername());
		if (null != usertmp) {
			if (usertmp.getPassword().equals(user.getPassword())) {
				if (usertmp.getIsActivated() == 1) {
					message = jwtService.generateTokenLogin(user.getUsername());
					status = HttpStatus.OK;
				} else {
					message = "User name is locked! ";
					status = HttpStatus.LOCKED;
				}
			} else {
				message = "Password incorrect! ";
				status = HttpStatus.UNAUTHORIZED;
			}
		} else {
			message = "User not exist! ";
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<String>(message, status);
	}

	/* ---------------- EDIT USER ------------------------ */
	@RequestMapping(value = "/users/edit", method = RequestMethod.POST)
	public ResponseEntity<String> editUserManagement(HttpServletRequest request, @RequestBody Users user) {
		try {
			HttpSession session = request.getSession();
			String userName = (String) session.getAttribute("usernameLogin");
			user.setUpdatedBy(userName);
			usersService.updateUserInfo(user);
			return new ResponseEntity<String>("Update!", HttpStatus.OK);
		} catch (CustomException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.message, HttpStatus.BAD_REQUEST);
		}
	}

	/* ---------------- ACTIVATE/DEACTIVATE USER ------------------------ */
	@RequestMapping(value = "/users/activate-deactivate-user", method = RequestMethod.POST)
	public ResponseEntity<String> activateDeactivateUser(HttpServletRequest request, @RequestBody Users user) {
		try {
			if (usersService.activateDeactivate(user) == 1)
				return new ResponseEntity<String>("Success", HttpStatus.OK);
			else
				return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
