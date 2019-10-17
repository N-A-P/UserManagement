package com.mockproject.du1.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mockproject.du1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mockproject.du1.services.EmailService;
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

    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public ResponseEntity sendMail(@RequestBody MailOfUser mailOfUser) {
        if (emailService.sendEmailToAll(mailOfUser.getUsers(), mailOfUser.getEmailHeader(), mailOfUser.getEmailBodyText()))
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        return new ResponseEntity("Failen", HttpStatus.BAD_REQUEST);
    }

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public MailOfUser sendMail() {
//        return MailOfUser.builder().emailBodyText("12312").emailHeader("header").users(List.of(Users.builder().userId(1).firstName("duc").lastName("le").build())).build();
//    }

    @RequestMapping(value = "/coverExcel", method = RequestMethod.POST)
    public ResponseEntity coverExcel(@RequestBody MultipartFile file) throws IOException {
        List<String> emails = emailService.coverExcellFileToArray(file.getBytes());
        List<Users> users = new ArrayList<>();

        users = usersService.getUsersListByEmails(emails);
        return new ResponseEntity<List<Users>>(users, HttpStatus.OK);

    }

    @RequestMapping(value = "/coverExcel/test", method = RequestMethod.POST)
    public ResponseEntity coverExcelTest(@RequestBody byte[] bytes) throws IOException {
        List<String> emails = emailService.coverExcellFileToArray(bytes);
        return new ResponseEntity<List<String>>(emails, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllEmailContent", method = RequestMethod.GET)
    public ResponseEntity getAllEmailContent() {
        return new ResponseEntity<List<EmailTemplate>>(emailService.getAllemailContent(), HttpStatus.OK);
    }

    @RequestMapping(value = "/editTopicById", method = RequestMethod.GET)
    public ResponseEntity editTopic(@RequestParam EmailTemplate email) {
       if(emailService.editTopic(email).equalsIgnoreCase("success"))
        return new ResponseEntity<String>("success", HttpStatus.OK);
        return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
    }

}
