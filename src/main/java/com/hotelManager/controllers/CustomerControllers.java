package com.hotelManager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/manager")
@Slf4j
public class CustomerControllers {

    @GetMapping(value = "/customer")
    public ResponseEntity<String> getListCustomer() throws Exception {

        return ResponseEntity.ok("Success !!");
    }

    @GetMapping(value = "/customer/search")
    public ResponseEntity<String> getCustomer() throws Exception {

        return ResponseEntity.ok("Success !!");
    }

    @DeleteMapping(value = "/customer")
    public ResponseEntity<String> deleteCustomer() throws Exception {

        return ResponseEntity.ok("Success !!");
    }

    @PostMapping(value = "/customer")
    public ResponseEntity<String> addCustomer() throws Exception {

        return ResponseEntity.ok("Success !!");
    }

    @PutMapping(value = "/customer")
    public ResponseEntity<String> updateCustomer() throws Exception {

        return ResponseEntity.ok("Success !!");
    }
}
