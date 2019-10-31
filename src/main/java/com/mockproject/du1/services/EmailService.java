package com.mockproject.du1.services;

import com.mockproject.du1.common.DataUtil;
import com.mockproject.du1.mapper.CampaignCustomerMapper;
import com.mockproject.du1.mapper.CampaignMapper;
import com.mockproject.du1.mapper.CustomerMapper;
import com.mockproject.du1.mapper.EmailTemplateMapper;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.util.Assert;

import static com.mockproject.du1.common.DataUtil.nonEmpty;
import static com.mockproject.du1.common.DataUtil.notNull;
import static com.mockproject.du1.common.MessageUtils.getMessage;

@Service
public class EmailService {
    String currentTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    @Autowired
    EmailTemplateMapper emailMapper;
    @Autowired
    CampaignCustomerMapper mailHistoryMapper;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    CampaignMapper campaignMapper;

    public boolean sendEmailToAll(List<Customer> Customers, int sendEmailUserId, int campaignId) {
        if (Customers != null) {
            try {
                EmailTemplate email = emailMapper.sqlGetEmailTemplateSelectByCampaintId(campaignId);
                LocalDate localDate = LocalDate.now();
                Date date = new Date(localDate.atStartOfDay(ZoneId.of("America/New_York")).toEpochSecond() * 1000);
                for (Customer customer : Customers) {
                    sendEmail(customer, email.getTitle(), email.getBody(), campaignId, date, sendEmailUserId);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }
    public boolean sendListCampaign(List<MailOfUser> mailOfUsers) {
        if (mailOfUsers != null) {
       for(MailOfUser mailOfUser:mailOfUsers){
           if(!sendEmailToAll(mailOfUser.getCustomers(), mailOfUser.getSendEmailUserId(), mailOfUser.getCampaignId())) return false;
       }
            return true;
        }
        return false;
    }

    private void sendEmail(Customer customer, String emailHeader, String emailBodyText, int campaignId, Date sendTime, int sendUserId) throws IOException {

        Mail mail = prepareMail(customer, emailHeader, emailBodyText);

        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
//        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
//        Response response = sg.api(request);
        mailHistoryMapper.sqlInsertMailHistoryl(CampaignCustomer.builder()
                .sendTime(sendTime)
                .userId(sendUserId)
                .createTimestamp(currentTimestamp)
                .updateTimestamp(currentTimestamp)
                .campaignId(campaignId)
                .customerId(customer.getCustomerId())
                .build());

    }

    private Mail prepareMail(Customer customer, String emailHeader, String emailBodyText) {
        // customized email content with user first name, last name
        final String signature = "Best Regards, \n My Bank Application.";
        StringBuilder finalText = new StringBuilder();
        finalText.append("Dear " + customer.getFirstName() + " " + customer.getLastName() + "," + "\n");
        finalText.append(emailBodyText + "\n");
        finalText.append(signature);
        Email fromEmail = new Email("moneydontsleep8888@gmail.com");
        Email toEmail = new Email(customer.getCustomerEmail());
        if (!isValid(customer.getCustomerEmail())) {
            throw new IllegalArgumentException("email inValid");
        }
        Content content = new Content("text/plain", finalText.toString());
        Mail mail = new Mail(fromEmail, emailHeader, toEmail, content);
        return mail;
    }

    public XSSFWorkbook coverExcellFileToArray(byte[] file) throws IOException {
        List<String> error = new ArrayList<>();
        try {
            int rowCount = 1;
            String email = null;
            InputStream is = new ByteArrayInputStream(file);

            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(1);
            sheet.getRow(1).createCell(8);
            sheet.getRow(1).getCell(8).setCellValue("Message");
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Boolean nonErrorCustomer = true;
                Row row = rowIterator.next();
                row.cellIterator();
                String errorCause = "detail:";
                try {
                    if (!notNull(row.getCell(2))) {
                        errorCause+=getMessage("emailService.email.null",rowCount);
                        nonErrorCustomer = false;
                    } else {
                        email = row.getCell(2).getStringCellValue();
                        if (!isValid(row.getCell(2).getStringCellValue())) {
                            errorCause+=getMessage("emailService.email.inValid",rowCount);
                            nonErrorCustomer = false;
                        } else {
                            Customer customer = customerMapper.sqlGetCustomerByEmailSelect(email);
                            if (notNull(customer)) {
                                errorCause+=getMessage("emailService.email.exit",rowCount);
                                nonErrorCustomer = false;
                            }
                        }
                    }
                    String firstName = "";
                    String LastName = "";
                    if (!nonEmpty(row.getCell(3).getStringCellValue())) {
                        if (!nonErrorCustomer) {
                            errorCause+=" And "+getMessage("emailService.name.null",rowCount,3);
                        } else {
                            errorCause+=getMessage("emailService.name.null",rowCount,3);
                            nonErrorCustomer = false;
                        }
                    } else {
                        firstName = row.getCell(3).getStringCellValue();
                    }
                    if (!nonEmpty(row.getCell(4).getStringCellValue())) {
                        if (!nonErrorCustomer) {
                            errorCause+=" And "+getMessage("emailService.name.null",rowCount,4);
                        } else {
                            errorCause+=getMessage("emailService.name.null",rowCount,4);
                            nonErrorCustomer = false;
                        }
                    } else {
                        LastName = row.getCell(4).getStringCellValue();
                    }


                    if (nonErrorCustomer) {
                        Customer customer = Customer.builder()
                                .customerEmail(email)
                                .firstName(firstName)
                                .lastName(LastName)
                                .dob(row.getCell(5).getStringCellValue() == "" ? null : row.getCell(5).getStringCellValue())
                                .address(row.getCell(6).getStringCellValue())
                                .createTimestamp(currentTimestamp)
                                .updateTimestamp(currentTimestamp)
                                .company(row.getCell(7).getStringCellValue())
                                .build();
                        customerMapper.sqlCreateCustomerInsert(customer);
                        errorCause = "success";
                    }
                    row.createCell(8);
                    row.getCell(8).setCellValue(errorCause);
                } catch (Exception e) {
                    error.add(e.getMessage());
                }
                rowCount++;
            }


            return workbook;
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

    public String addTopic(EmailTemplate email) {

        emailMapper.sqlCreateEmailInsert(email);
        return "success";
    }

    public String editCampaign(Campaign campaign) {
        campaignMapper.sqlCampaignInfoUpdate(campaign);
        return "success";
    }
    public String editEmailTemplateCampaign(Campaign campaign) {
        campaignMapper.sqlCampaignInfoUpdate(campaign);
        return "success";
    }

    public String addCampaign(Campaign campaign) {
        if(campaign.getStartDate().before(campaign.getEndDate())){
            int getDiff = campaign.getEndDate().getDate() - campaign.getStartDate().getDate();
            campaign.setDuration(getDiff);
            campaignMapper.sqlCreateCampaignInsert(campaign);
            return campaign.getCampaignId()+"";
        }

        return "start date mush befor end date";
    }

    public List<EmailTemplate> getAllTopic() {
        return emailMapper.sqlGetAllEmailTemplateSelect();
    }

    public List<Customer> getAllCustomer() {
        return customerMapper.sqlGetAllCustomer();
    }

    public List<CampaignDetail> getAllCampaignDetail() {
        List<CampaignDetail> campaignDetails = new ArrayList<>();
        List<Campaign> campaigns = campaignMapper.sqlGetAllCampain();
        List<Customer> customers = customerMapper.sqlGetAllCustomer();
        List<Integer> integers = new ArrayList<>();
        for (Campaign campaign : campaigns) {
            CampaignDetail campaignDetail = CampaignDetail.builder()
                    .campaign(campaign)
                    .customerList(customers).customerCheck(customerMapper.sqlGetCustomerByCampaignAndMaxTime(campaign.getCampaignId())).build();
            campaignDetails.add(campaignDetail);
        }
        return campaignDetails;
    }
    public List<Customer> coverExcellFileToArrayList(byte[] file) throws IOException {
        List<Customer> customers = new ArrayList<>();
        try {
            int rowCount = 1;
            String email = null;
            InputStream is = new ByteArrayInputStream(file);

            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(1);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            rowIterator.next();

            while (rowIterator.hasNext()) {

                try {
                    Boolean nonErrorCustomer = true;
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    email = row.getCell(2).getStringCellValue();
                    String firstName = row.getCell(3).getStringCellValue();
                    String LastName = row.getCell(4).getStringCellValue();
                    Customer customer = Customer.builder()
                            .customerEmail(email)
                            .firstName(firstName)
                            .lastName(LastName)
                            .dob(row.getCell(5).getStringCellValue() == "" ? null : row.getCell(5).getStringCellValue())
                            .address(row.getCell(6).getStringCellValue())
                            .createTimestamp(currentTimestamp)
                            .updateTimestamp(currentTimestamp)
                            .company(row.getCell(7).getStringCellValue())
                            .build();
                    customers.add(customer);
                }catch (Exception e){

                }



            }

          return customers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
