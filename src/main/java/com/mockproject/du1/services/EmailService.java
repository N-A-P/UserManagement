package com.mockproject.du1.services;

import com.mockproject.du1.model.Department;
import com.mockproject.du1.model.Users;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

@Service
<<<<<<< HEAD
public class EmailService {	
	/*=================IMPORTANT===================*/
//	TO USE THIS FUNCTION ON YOUR PC, RUN THESE CMD COMMANDS FIRST
//	
//	echo "export SENDGRID_API_KEY='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'" > sendgrid.env
//
//	echo "sendgrid.env" >> .gitignore
//
//	setx SENDGRID_API_KEY "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"
=======
public class EmailService {

	private Log log=LogFactory.getLog(EmailService.class);
>>>>>>> branch 'master' of https://github.com/ntgptit/mock

	private Log log=LogFactory.getLog(EmailService.class);
	
	/* ---------------- SEND PREPARED EMAIL TO LIST OF USERS ------------------------ */
	public boolean sendEmailToAll(List<Users> recipientList, String emailHeader, String emailBodyText) {
		if (recipientList != null) {
			try {
				for (Users user : recipientList) {
					sendEmail(user, emailHeader, emailBodyText);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}

	/* ---------------- SEND PREPARED EMAIL TO ONE USERS ------------------------ */
	private void sendEmail(Users recipient, String emailHeader, String emailBodyText) {
		try {
			Mail mail = prepareMail(recipient, emailHeader, emailBodyText);

			Request request = new Request();
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());

			SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));

			Response response = sg.api(request);

		} catch (IOException e) {
			log.error("error sending email to " + recipient.getFirstName() + " " + recipient.getLastName());
			e.printStackTrace();
		}
		log.info("mail has sent to " + recipient.getFirstName() + " " + recipient.getLastName());
	}

	/* ---------------- PREPARE EMAIL OBJECT WITH CUSTOMIZED HEADER AND BODY ------------------------ */
	private Mail prepareMail(Users recipient, String emailHeader, String emailBodyText) {
		// customized email content with user first name, last name
		final String signature = "Best Regards, \n My Bank Application.";
		StringBuilder finalText = new StringBuilder();
		finalText.append("Dear " + recipient.getFirstName() + " " + recipient.getLastName() + "," + "\n");
		finalText.append(emailBodyText + "\n");
		finalText.append(signature);

		Email fromEmail = new Email("moneydontsleep8888@gmail.com");
		Email toEmail = new Email(recipient.getUsername());
		Content content = new Content("text/plain", finalText.toString());
		Mail mail = new Mail(fromEmail, emailHeader, toEmail, content);
		return mail;
	}
    public List<String> coverExcellFileToArray(File file) throws IOException {
        List<String> emails=new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                emails.add(cell.getStringCellValue());
            }
        }
        return emails;
    }
}
