package com.hotelManager.controllers;

import com.hotelManager.dtos.request.RegistrationRequest;
import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.dtos.responses.QLKSRegistrationResponse;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.services.QLKSRegistrationFormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/manager")
@Slf4j
public class RegistrationRoomControllers {

    @Autowired
    QLKSRegistrationFormService qlksRegistrationFormService;

    @GetMapping(value = "/register")
    public ResponseEntity<List<QLKSRegistrationResponse>> getList() throws HotelManagerException {
        return ResponseEntity.ok(qlksRegistrationFormService.getAll());
    }

    @PostMapping(value = "/register")
    public ResponseEntity<BaseApiResponse> add(@RequestBody @Valid RegistrationRequest request) throws HotelManagerException {
        qlksRegistrationFormService.add(request);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @GetMapping(value = "/register/{id}")
    public ResponseEntity<QLKSRegistrationResponse> getDetail(@PathVariable("id") String id) throws HotelManagerException {
        return ResponseEntity.ok(qlksRegistrationFormService.getDetail(id));
    }

    @DeleteMapping(value = "/register/{id}")
    public ResponseEntity<BaseApiResponse> delete(@PathVariable("id") String id) throws HotelManagerException {
        qlksRegistrationFormService.delete(id);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PutMapping(value = "/register/{id}")
    public ResponseEntity<BaseApiResponse> update(@PathVariable("id") String id, @RequestBody RegistrationRequest request) throws HotelManagerException {

        qlksRegistrationFormService.update(id, request);
        return ResponseEntity.ok(new BaseApiResponse());
    }
}
