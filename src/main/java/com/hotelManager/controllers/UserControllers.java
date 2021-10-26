package com.hotelManager.controllers;

import com.hotelManager.constants.ResponseCodes;
import com.hotelManager.dtos.request.AddUserRequest;
import com.hotelManager.dtos.request.UserRequest;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.exceptions.ValidateException;
import com.hotelManager.model.QLKSEmployeeModel;
import com.hotelManager.services.QLKSEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserControllers {

    @Autowired
    QLKSEmployeeService qlksEmployeeService;

    @PostMapping(value = "/login")
    public ResponseEntity<QLKSEmployeeModel> getUser(@RequestBody @Valid UserRequest userRequest, Errors errors) throws HotelManagerException {
        if (errors.hasErrors()) {
            throw new ValidateException(ResponseCodes.PARAMETER_INVALID, errors.getFieldError().getDefaultMessage());
        }

        return ResponseEntity.ok(qlksEmployeeService.login(userRequest));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> addEmployee(@RequestBody @Valid AddUserRequest request, Errors errors) throws HotelManagerException {
        if (errors.hasErrors()) {
            throw new ValidateException(ResponseCodes.PARAMETER_INVALID, errors.getFieldError().getDefaultMessage());
        }

        qlksEmployeeService.save(request);
        return ResponseEntity.ok("Success !!");
    }


}
