package com.mockproject.du1.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<List<Users>> getAllUser() {
		return new ResponseEntity<List<Users>>(usersService.getAllUser(), HttpStatus.OK);
	}

	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	public ResponseEntity<String> sendMail(@RequestBody MailOfUser mailOfUser) {
		if (emailService.sendEmailToAll(mailOfUser.getUsers(), mailOfUser.getEmailHeader(),
				mailOfUser.getEmailBodyText())) {
			return new ResponseEntity<String>("ERROR", HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}

	/* ---------------- REGISTRATION NEW USER ------------------------ */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerNewCustomer(@RequestBody Users user) {
		if (usersService.isValidEmail(user.getEmail())) {
			try {
				usersService.registerNewCustomer(user);
				return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
			} catch (Exception e) {
				if (e.getCause().toString().contains("org.postgresql.util.PSQLException")) {
					log.error(e.getMessage());
					return new ResponseEntity<String>("Username or Email Existed!", HttpStatus.BAD_REQUEST);
				} else {
					log.error(e.getMessage());
					return new ResponseEntity<String>("SERVER ERROR!", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} else {
			log.warn("Invalid email format");
			return new ResponseEntity<String>("Invalid email format!", HttpStatus.BAD_REQUEST);
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

	/* ---------------- USER MANAGEMENT GET REQUEST ------------------------ */
	@RequestMapping(value = "/user-management", method = RequestMethod.GET)
	public ResponseEntity<List<UsersFull>> getUserManagement(HttpServletRequest request) {
		return new ResponseEntity<List<UsersFull>>(usersService.getAllUserFull(), HttpStatus.OK);
	}

	/* ---------------- USER MANAGEMENT POST REQUEST ------------------------ */
	@RequestMapping(value = "/user-management", method = RequestMethod.POST)
	public ResponseEntity<String> editUserManagement(HttpServletRequest request, @RequestBody UsersFull userFull) {
		if (usersService.updateUserInfo(userFull))
			return new ResponseEntity<String>("Edit SUCCESS!", HttpStatus.CREATED);
		else
			return new ResponseEntity<String>("Email Existed!", HttpStatus.BAD_REQUEST);
	}

	/* ---------------- DELETE USER ------------------------ */
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(HttpServletRequest request, @RequestBody UsersFull userFull) {
		if (usersService.deleteUser(userFull))
			return new ResponseEntity<String>("Remove User SUCCESS!", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Remove User ERROR", HttpStatus.BAD_REQUEST);
	}
	
	/* ---------------- SEARCH IN USER LIST ------------------------ */
	@RequestMapping(value = "/user-management/search", method = RequestMethod.POST)
	public ResponseEntity<List<Users>> getSearchResult(HttpServletRequest request, @RequestBody String toSearch) {
		return new ResponseEntity<List<Users>>(usersService.getSearchResult(toSearch), HttpStatus.OK);
	}

}
