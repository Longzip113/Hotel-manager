package com.hotelManager.controllers;

import com.hotelManager.dtos.request.AddUserRequest;
import com.hotelManager.dtos.request.UserRequest;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSEmployeeModel;
import com.hotelManager.services.QLKSEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserControllers {

    @Autowired
    QLKSEmployeeService qlksEmployeeService;

    @PostMapping(value = "/login")
    public ResponseEntity<QLKSEmployeeModel> getUser(@RequestBody UserRequest userRequest) throws HotelManagerException {
        return ResponseEntity.ok(qlksEmployeeService.login(userRequest));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> addEmployee(@RequestBody AddUserRequest request) throws HotelManagerException {

        qlksEmployeeService.save(request);
        return ResponseEntity.ok("Success !!");
    }


}
