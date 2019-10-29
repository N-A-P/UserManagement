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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.du1.exception.CustomException;
import com.mockproject.du1.model.MailOfUser;
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
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<UsersFull>> getAllUser(@RequestParam int isActivated) {
		return new ResponseEntity<List<UsersFull>>(usersService.getAllUserFull(isActivated), HttpStatus.OK);
	}

	/* ---------------- REGISTRATION NEW USER ------------------------ */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerNewCustomer(@RequestBody Users user) {
		try {
			usersService.registerNewCustomer(user);
			return new ResponseEntity<String>("Created!", HttpStatus.OK);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.message, HttpStatus.BAD_REQUEST);
		}
	}

	/* ---------------- SEARCH IN USER LIST ------------------------ */
	@RequestMapping(value = "/user-management/search", method = RequestMethod.POST)
	public ResponseEntity<List<Users>> getSearchResult(HttpServletRequest request, @RequestBody String toSearch) {
		return new ResponseEntity<List<Users>>(usersService.getSearchResult(toSearch), HttpStatus.OK);
	}

	/* ---------------- LOGIN ------------------------ */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(HttpServletRequest request, @RequestBody Users user) {
		String result = "";
		HttpStatus httpStatus = null;

		try {
			if (usersService.checkLogin(user)) {
				Users userToCheck = usersService.getUserByUsername(user.getUsername());
				if (userToCheck.getIsActivated() == 1) {
					result = jwtService.generateTokenLogin(user.getUsername());
					httpStatus = HttpStatus.OK;
				} else {
					result = "Account Deactivated";
					httpStatus = HttpStatus.BAD_REQUEST;
				}
			} else {
				result = "Wrong username or password";
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception ex) {
			result = "Server Error";
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(result, httpStatus);
	}

	/* ---------------- EDIT USER ------------------------ */
	@RequestMapping(value = "/user-management", method = RequestMethod.POST)
	public ResponseEntity<String> editUserManagement(HttpServletRequest request, @RequestBody Users user) {
		try {
			HttpSession session = request.getSession();
			String userName = (String) session.getAttribute("usernameLogin");
			user.setUpdateBy(userName);
			usersService.updateUserInfo(user);
			return new ResponseEntity<String>("Update!", HttpStatus.OK);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.message, HttpStatus.BAD_REQUEST);
		}
	}

}
