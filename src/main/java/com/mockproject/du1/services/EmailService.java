package com.mockproject.du1.services;

import com.mockproject.du1.mapper.EmailMapper;
import com.mockproject.du1.mapper.MailHistoryMapper;
import com.mockproject.du1.model.Department;
import com.mockproject.du1.model.MailHistory;
import com.mockproject.du1.model.Users;
import org.apache.commons.logging.LogFactory;
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
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

@Service
public class EmailService {
    @Autowired
    EmailMapper emailMapper;
    @Autowired
    MailHistoryMapper mailHistoryMapper;

    public boolean sendEmailToAll(List<Users> recipientList, String emailHeader, String emailBodyText) {
        if (recipientList != null) {
            try {
                com.mockproject.du1.model.Email email = com.mockproject.du1.model.Email.builder()
                        .content(emailBodyText)
                        .subject(emailHeader)
                        .build();
                emailMapper.sqlCreateEmailInsert(email);

                for (Users user : recipientList) {
                        sendEmail(user, emailHeader, emailBodyText,email.getEmailId());
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    private void sendEmail(Users recipient, String emailHeader, String emailBodyText,int emailId) throws IOException {

            Mail mail = prepareMail(recipient, emailHeader, emailBodyText);

            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
            Response response = sg.api(request);
            mailHistoryMapper.sqlInsertMailHistoryl(MailHistory.builder().EmailId(emailId).UserId(recipient.getUserId()).SendDate(new java.util.Date()).build());

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
        if(!isValid(recipient.getEmail())){
            throw new IllegalArgumentException("email inValid");
        }
        Content content = new Content("text/plain", finalText.toString());
        Mail mail = new Mail(fromEmail, emailHeader, toEmail, content);
        return mail;
    }

    public List<String> coverExcellFileToArray(byte[] file) throws IOException {
        try {
            List<String> emails = new ArrayList<>();
            InputStream is = new ByteArrayInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (isValid(cell.getStringCellValue())) {
                        emails.add(cell.getStringCellValue());
                    }
                }
            }
            return emails;
        }catch(Exception e){
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

    public List<com.mockproject.du1.model.Email> getAllemailContent() {
       return emailMapper.sqlGetAllEmailSelect();
    }
}
