package com.hotelManager.controllers;

import com.hotelManager.dtos.request.ChangeRoomRequest;
import com.hotelManager.dtos.request.LogCustomerRequest;
import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.dtos.responses.QLKSLogCustomerResponse;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.services.QLKSLogCustomerService;
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
public class UseServiceControllers {

    @Autowired
    QLKSLogCustomerService qlksLogCustomerService;

    @PostMapping(value = "/use")
    public ResponseEntity<BaseApiResponse> add(@RequestBody @Valid LogCustomerRequest request) throws HotelManagerException {
        qlksLogCustomerService.add(request);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @GetMapping(value = "/use")
    public ResponseEntity<List<QLKSLogCustomerResponse>> getList() throws HotelManagerException {
        return ResponseEntity.ok(qlksLogCustomerService.getAll());
    }

    @GetMapping(value = "/use/{id}")
    public ResponseEntity<QLKSLogCustomerResponse> getDetail(@PathVariable("id") String id) throws HotelManagerException {
        return ResponseEntity.ok(qlksLogCustomerService.getDetail(id));
    }

    @PostMapping(value = "/use/change")
    public ResponseEntity<BaseApiResponse> changeRoom(@RequestBody @Valid ChangeRoomRequest request) throws HotelManagerException {
        qlksLogCustomerService.changeRoom(request);
        return ResponseEntity.ok(new BaseApiResponse());
    }
}
