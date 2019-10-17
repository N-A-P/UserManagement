package com.mockproject.du1.services;

import com.mockproject.du1.mapper.CustomerMapper;
import com.mockproject.du1.mapper.EmailMapper;
import com.mockproject.du1.mapper.MailHistoryMapper;
import com.mockproject.du1.model.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.du1.model.Users;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.util.Assert;

@Service
public class EmailService {
    @Autowired
    EmailMapper emailMapper;
    @Autowired
    MailHistoryMapper mailHistoryMapper;
    @Autowired
    CustomerMapper customerMapper;

    public boolean sendEmailToAll(List<Users> recipientList, String emailHeader, String emailBodyText) {
        if (recipientList != null) {
            try {
                EmailTemplate email = EmailTemplate.builder()
                        .title(emailBodyText)
                        .body(emailHeader)
                        .build();
                emailMapper.sqlCreateEmailInsert(email);

                for (Users user : recipientList) {
                    sendEmail(user, emailHeader, emailBodyText, email.getEmaiTemplateId());
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    private void sendEmail(Users recipient, String emailHeader, String emailBodyText, int emailId) throws IOException {

        Mail mail = prepareMail(recipient, emailHeader, emailBodyText);

        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Response response = sg.api(request);
        mailHistoryMapper.sqlInsertMailHistoryl(CampaignCustomer.builder().campaignId(emailId).customerId(recipient.getUserId()).sendTime(new java.util.Date()).build());

    }

    private Mail prepareMail(Users recipient, String emailHeader, String emailBodyText) {
        // customized email content with user first name, last name
        final String signature = "Best Regards, \n My Bank Application.";
        StringBuilder finalText = new StringBuilder();
        finalText.append("Dear " + recipient.getFirstName() + " " + recipient.getLastName() + "," + "\n");
        finalText.append(emailBodyText + "\n");
        finalText.append(signature);
        Email fromEmail = new Email("moneydontsleep8888@gmail.com");
        Email toEmail = new Email(recipient.getEmail());
        if (!isValid(recipient.getEmail())) {
            throw new IllegalArgumentException("email inValid");
        }
        Content content = new Content("text/plain", finalText.toString());
        Mail mail = new Mail(fromEmail, emailHeader, toEmail, content);
        return mail;
    }

    public List<String> coverExcellFileToArray(byte[] file) throws IOException {
        List<String> error = new ArrayList<>();
        try {
            int rowCount = 0;
            InputStream is = new ByteArrayInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                try {
                    Assert.notNull(row.getCell(0), "error null email in row : " + rowCount);
                    String email = row.getCell(0).getStringCellValue();
                    if (!isValid(row.getCell(0).getStringCellValue())) {
                        throw new IllegalArgumentException("email inValid in row : " + rowCount);
                    }
                    Assert.notNull(row.getCell(1), "error null name in row : " + rowCount);
                    String name = row.getCell(1).getStringCellValue();
                    Customer customer = customerMapper.sqlGetCustomerByEmailSelect(email);
                    Assert.isNull(customer, "Customer Exit in row : " + rowCount);
                    customer = Customer.builder().customerEmail(email).customerName(name).build();
                    customerMapper.sqlCreateCustomerInsert(customer);
                    error.add("add success customer :" + customer.getCustomerName());
                } catch (Exception e) {
                    error.add(e.getMessage());
                }

                rowCount++;

            }


            return error;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public EmailTemplate getEmailById(int emailId) {
        return emailMapper.sqlGetEmailSelectById(emailId);

    }

    public String editTopic(EmailTemplate email) {

        if (emailMapper.sqlGetEmailSelectById(email.getEmaiTemplateId()) == null) {
            return "email doesnt exit";
        }
        emailMapper.sqlEmailUpdate(email);
        return "success";
    }

    public List<EmailTemplate> getAllemailContent() {
        return emailMapper.sqlGetAllEmailSelect();
    }
}
