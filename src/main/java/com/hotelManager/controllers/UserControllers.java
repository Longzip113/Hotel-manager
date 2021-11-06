package com.hotelManager.controllers;

import com.hotelManager.dtos.request.*;
import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSEmployeeModel;
import com.hotelManager.services.QLKSEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserControllers {

    @Autowired
    QLKSEmployeeService qlksEmployeeService;

    @PostMapping(value = "/login")
    public ResponseEntity<QLKSEmployeeModel> login(@RequestBody UserRequest userRequest) throws HotelManagerException {
        return ResponseEntity.ok(qlksEmployeeService.login(userRequest));
    }

    @PostMapping(value = "/")
    public ResponseEntity<BaseApiResponse> add(@RequestBody AddUserRequest request) throws HotelManagerException {
        qlksEmployeeService.save(request);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<QLKSEmployeeModel>> getList() throws HotelManagerException {
        return ResponseEntity.ok(qlksEmployeeService.getAll());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BaseApiResponse> delete(@PathVariable("id") String id) throws HotelManagerException {
        qlksEmployeeService.delete(id);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<QLKSEmployeeModel> getDetail(@PathVariable("id") String id) throws HotelManagerException {
        return ResponseEntity.ok(qlksEmployeeService.getDetail(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BaseApiResponse> update(@PathVariable("id") String id, @RequestBody UpdateUserRequest updateUserRequest) throws HotelManagerException {

        qlksEmployeeService.update(updateUserRequest, id);

        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PostMapping(value = "/get-verification")
    public ResponseEntity<BaseApiResponse> getVerification(@RequestBody GetVerificationRequest getVerificationRequest) throws HotelManagerException {
        qlksEmployeeService.getVerification(getVerificationRequest);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PostMapping(value = "/change-pass")
    public ResponseEntity<BaseApiResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws HotelManagerException {
        qlksEmployeeService.changDefaultPassword(changePasswordRequest);
        return ResponseEntity.ok(new BaseApiResponse());
    }

}
