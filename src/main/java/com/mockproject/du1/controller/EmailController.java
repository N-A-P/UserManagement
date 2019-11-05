package com.mockproject.du1.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.mockproject.du1.model.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mockproject.du1.services.EmailService;
import com.mockproject.du1.services.UsersService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/email")
public class EmailController {
    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public ResponseEntity sendMail(@RequestBody MailOfUser mailOfUser) {
        if (emailService.sendEmailToAll(mailOfUser.getCustomers(), mailOfUser.getSendEmailUserId(), mailOfUser.getCampaignId()))
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        return new ResponseEntity("Bạn không có gửi mail", HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/send-campaign", method = RequestMethod.POST)
    public ResponseEntity sendCampaign(@RequestBody List<MailOfUser> mailOfUsers) {
        if (emailService.sendListCampaign(mailOfUsers))
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        return new ResponseEntity("Bạn không có gửi mail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public MailOfUser sendMail() {
        List<Customer> customers=new ArrayList<>();
        customers.add(Customer.builder()
                .id(13)
                .email("admin123@gmail.com")
                .build());
        return MailOfUser.builder().campaignId(1).sendEmailUserId(1).customers(customers).build();
    }
    @RequestMapping(value = "/cover-excel-to-DB", method = RequestMethod.POST)
    public byte[] coverExcel(@RequestBody MultipartFile file, HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = emailService.coverExcellFileToArray(file.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        byte[] content = outputStream.toByteArray();
        Files.write(Paths.get("test.xlsx"),  content);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + "test.xlsx" + "\"");
        return content;
    }
    @RequestMapping(value = "/cover-excel-to-FE", method = RequestMethod.POST)
    public ResponseEntity coverExcelToFE(@RequestBody MultipartFile file) throws IOException {
        List<Customer> customers=emailService.coverExcellFileToArrayList(file.getBytes());
     if(customers!=null){
         return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
     }
        return new ResponseEntity<String>("Enter the correct Excel file format", HttpStatus.OK);
    }

    @RequestMapping(value = "/cover-excel-to-File", method = RequestMethod.GET)
    public byte[] coverExcelTest(HttpServletResponse response) throws IOException {
        Path path = Paths.get("test.xlsx");
        byte[] bytesArray = Files.readAllBytes(path);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.close();
        outputStream.write(bytesArray);
        byte[] content = outputStream.toByteArray();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + "test.xlsx" + "\"");
        return content;
    }
    @RequestMapping(value = "/coverExceltoDB/test", method = RequestMethod.POST)
    public ResponseEntity coverExcelToFETest(@RequestBody byte[] bytes) throws IOException {
        emailService.coverExcellFileToArray(bytes);
        return new ResponseEntity<String>("Enter the correct Excel file format", HttpStatus.BAD_REQUEST);
    }



    @RequestMapping(value = "/edit-topic", method = RequestMethod.POST)
    public ResponseEntity editTopic(@RequestBody EmailTemplate email) {
        if (emailService.editTopic(email).equalsIgnoreCase("success"))
            return new ResponseEntity<List<EmailTemplate>>(emailService.getAllTopic(), HttpStatus.OK);
        return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/add-topic", method = RequestMethod.POST)
    public ResponseEntity addTopic(@RequestBody EmailTemplate email) {
        if (emailService.addTopic(email).equalsIgnoreCase("success"))
            return new ResponseEntity<List<EmailTemplate>>(emailService.getAllTopic(), HttpStatus.OK);
        return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/get-all-topic", method = RequestMethod.GET)
    public ResponseEntity getAllTopic() {
            return new ResponseEntity<List<EmailTemplate>>(emailService.getAllTopic(), HttpStatus.OK);
    }
    @RequestMapping(value = "/edit-campaign", method = RequestMethod.POST)
    public ResponseEntity editCampaign(@RequestBody Campaign campaign) {
        if (emailService.editCampaign(campaign).equalsIgnoreCase("success")){
            List<CampaignDetail> campaignDetails = emailService.getAllCampaignDetail();
        if (campaignDetails != null) {
            return new ResponseEntity<List<CampaignDetail>>(campaignDetails, HttpStatus.OK);
        }
        }
        return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/edit-email-template-campaign", method = RequestMethod.POST)
    public ResponseEntity editEmailTemplateCampaign(@RequestBody Campaign campaign) {
        if (emailService.editEmailTemplateCampaign(campaign).equalsIgnoreCase("success")){
            List<CampaignDetail> campaignDetails = emailService.getAllCampaignDetail();
            if (campaignDetails != null) {
                return new ResponseEntity<List<CampaignDetail>>(campaignDetails, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/add-campaign", method = RequestMethod.POST)
    public ResponseEntity addCampaign(@RequestBody Campaign campaign) {
        String result=emailService.addCampaign(campaign);
        if (!result.equalsIgnoreCase("start date mush befor end date")) {
            List<CampaignDetail> campaignDetails = emailService.getAllCampaignDetail();
            if (campaignDetails != null) {
                return new ResponseEntity<List<CampaignDetail>>(campaignDetails, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>("start date mush befor end date", HttpStatus.BAD_REQUEST);
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
