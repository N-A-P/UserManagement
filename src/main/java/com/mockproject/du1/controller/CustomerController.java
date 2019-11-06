package com.mockproject.du1.controller;

import com.mockproject.du1.common.DataUtil;
import com.mockproject.du1.model.Customer;
import com.mockproject.du1.model.EmailTemplate;
import com.mockproject.du1.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @RequestMapping(value = "/get-all-customer", method = RequestMethod.GET)
    public ResponseEntity getAllCustomer(@RequestParam(required = false) Long page,@RequestParam( required = false) Long size) {
        List<Customer> customers = customerService.getAllCustomer();
        if (DataUtil.isNullOrZero(page)) {
            page = 0L;
        }
        if (DataUtil.isNullOrZero(size)) {
            size = 10L;
        }
        Pageable pageable = new PageRequest(page.intValue(), size.intValue());
        if (pageable.getOffset() >= customers.size()) {
            return new ResponseEntity("EmptyPage", HttpStatus.OK);
        }
        if (customers != null) {
            int startIndex = (int) pageable.getOffset();
            int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize()) > customers.size() ?
                    customers.size() :
                    pageable.getOffset() + pageable.getPageSize());
            List subList = customers.subList(startIndex, endIndex);
            return new ResponseEntity(new PageImpl(subList, pageable, customers.size()), HttpStatus.OK);
        }
        return new ResponseEntity<String>("false", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/edit-customer", method = RequestMethod.GET)
    public ResponseEntity editCustomer(@RequestBody Customer customer) {
        String error=customerService.editCustomer(customer);
        if(error.equalsIgnoreCase("Success"))
        return new ResponseEntity<List<Customer>>(customerService.getAllCustomer(), HttpStatus.OK);
        return new ResponseEntity<String>(error, HttpStatus.OK);

    }
    @RequestMapping(value = "/delete-customer", method = RequestMethod.GET)
    public ResponseEntity editCustomer(@RequestParam int id) {
        String error=customerService.deleteCustomer(id);
        if(error.equalsIgnoreCase("Success"))
            return new ResponseEntity<List<Customer>>(customerService.getAllCustomer(), HttpStatus.OK);
        return new ResponseEntity<String>(error, HttpStatus.OK);

    }
}
