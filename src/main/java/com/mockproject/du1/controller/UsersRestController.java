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



	/* ---------------- SEARCH IN USER LIST ------------------------ */
	@RequestMapping(value = "/user-management/search", method = RequestMethod.POST)
	public ResponseEntity<List<Users>> getSearchResult(HttpServletRequest request, @RequestBody String toSearch) {
		return new ResponseEntity<List<Users>>(usersService.getSearchResult(toSearch), HttpStatus.OK);
	}

}
