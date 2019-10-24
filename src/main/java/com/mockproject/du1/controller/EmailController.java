package com.mockproject.du1.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.mockproject.du1.model.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mockproject.du1.services.EmailService;
import com.mockproject.du1.services.UsersService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
        if (emailService.sendEmailToAll(mailOfUser.getCustomers(), mailOfUser.getSendEmailUserId(), mailOfUser.getCampaignId()))
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        return new ResponseEntity("Failen", HttpStatus.BAD_REQUEST);
    }

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public MailOfUser sendMail() {
//        return MailOfUser.builder().campaignId(1).sendEmailUserId(1).customers(List.of(Customer.builder().customerId(13).customerEmail("admin123@gmail.com").build())).build();
//    }

    @RequestMapping(value = "/coverExcel", method = RequestMethod.POST)
    public byte[] coverExcel(@RequestBody MultipartFile file) throws IOException {
        XSSFWorkbook workbook = emailService.coverExcellFileToArray(file.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[21000];
        workbook.write(outputStream);
        outputStream.write(bytes);
        return bytes;

    }

    @RequestMapping(value = "/coverExcel/test", method = RequestMethod.POST)
    public void coverExcelTest(@RequestBody byte[] bytes) throws IOException {
        XSSFWorkbook workbook = emailService.coverExcellFileToArray(bytes);
        File file = new File("C:/employee.xlsx");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
    }

    @RequestMapping(value = "/getAllEmailContent", method = RequestMethod.GET)
    public ResponseEntity getAllEmailTemContent() {
        return new ResponseEntity<List<EmailTemplate>>(emailService.getAllemailContent(), HttpStatus.OK);
    }

    @RequestMapping(value = "/editTopic", method = RequestMethod.POST)
    public ResponseEntity editTopic(@RequestBody EmailTemplate email) {
        if (emailService.editTopic(email).equalsIgnoreCase("success"))
            return new ResponseEntity<String>("success", HttpStatus.OK);
        return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/addTopic", method = RequestMethod.POST)
    public ResponseEntity addTopic(@RequestBody EmailTemplate email) {
        if (emailService.addTopic(email).equalsIgnoreCase("success"))
            return new ResponseEntity<String>("success", HttpStatus.OK);
        return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/editCampaign", method = RequestMethod.POST)
    public ResponseEntity editCampaign(@RequestBody Campaign campaign) {
        if (emailService.editCampaign(campaign).equalsIgnoreCase("success"))
            return new ResponseEntity<String>("success", HttpStatus.OK);
        return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/addCampaign", method = RequestMethod.POST)
    public ResponseEntity addCampaign(@RequestBody Campaign campaign) {
        if (emailService.addCampaign(campaign).equalsIgnoreCase("success"))
            return new ResponseEntity<String>("success", HttpStatus.OK);
        return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getAllCustomer", method = RequestMethod.GET)
    public ResponseEntity getAllCustomer() {
        List<Customer> customers = emailService.getAllCustomer();
        if (customers != null) {
            return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
        }
        return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getAllCampaignDetail", method = RequestMethod.GET)
    public ResponseEntity getAllCampaignDetail() {
        List<CampaignDetail> campaignDetails = emailService.getAllCampaignDetail();
        if (campaignDetails != null) {
            return new ResponseEntity<List<CampaignDetail>>(campaignDetails, HttpStatus.OK);
        }
        return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
    }
}
