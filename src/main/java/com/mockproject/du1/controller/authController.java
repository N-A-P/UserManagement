package com.mockproject.du1.controller;

import com.sendgrid.Response;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/auth")
public class authController {
    @RequestMapping(value = "/check-auth", method = RequestMethod.POST)
    public ResponseEntity CheckAuthUrl(@RequestParam String page) {
        return ResponseEntity.ok("");
    }
}
