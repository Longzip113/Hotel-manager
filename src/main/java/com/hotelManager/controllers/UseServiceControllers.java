package com.hotelManager.controllers;

import com.hotelManager.dtos.request.LogCustomerRequest;
import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.services.impl.QLKSLogCustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/manager")
@Slf4j
public class UseServiceControllers {

    @Autowired
    QLKSLogCustomerServiceImpl qlksLogCustomerService;

    @PostMapping(value = "/use")
    public ResponseEntity<BaseApiResponse> add(@RequestBody @Valid LogCustomerRequest request) throws HotelManagerException {
        qlksLogCustomerService.add(request);
        return ResponseEntity.ok(new BaseApiResponse());
    }
}
