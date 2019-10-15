package com.mockproject.du1.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.mockproject.du1.model.MailOfUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mockproject.du1.model.Department;
import com.mockproject.du1.model.EmployeeOfDepartment;
import com.mockproject.du1.model.Users;
import com.mockproject.du1.services.DepartmentService;
import com.mockproject.du1.services.EmailService;
import com.mockproject.du1.services.JwtService;
import com.mockproject.du1.services.UsersService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private UsersService usersService;
    @Autowired
    EmailService emailService;
    @RequestMapping(value = "/sendMail", method = RequestMethod.GET)
    public ResponseEntity<String> sendMail(@RequestBody MailOfUser mailOfUser) {
        emailService.sendEmailToAll(mailOfUser.getUsers(), mailOfUser.getEmailHeader(), mailOfUser.getEmailBodyText());
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public MailOfUser sendMail() {
        return MailOfUser.builder().emailBodyText("12312").emailHeader("header").users(List.of(Users.builder().userId(1).firstName("duc").lastName("le").build())).build();
    }

    @RequestMapping(value = "/coverExcel", method = RequestMethod.POST)
    public ResponseEntity<List<Users>> coverExcel(@RequestBody MultipartFile file) throws IOException {

        List<String> emails = emailService.coverExcellFileToArray(file.getBytes());
        List<Users> users = new ArrayList<>();
        users = usersService.getUsersListByEmails(emails);
        return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
    }


}
