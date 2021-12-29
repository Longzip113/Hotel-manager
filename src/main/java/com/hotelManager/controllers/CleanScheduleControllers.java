package com.hotelManager.controllers;

import com.hotelManager.dtos.request.CheckCleanScheduleRequest;
import com.hotelManager.dtos.request.CleanScheduleRequest;
import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.dtos.responses.QLKSCleanScheduleResponse;
import com.hotelManager.dtos.responses.QLKSLogCleanScheduleResponse;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.services.QLKSClearScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/manager")
@Slf4j
public class CleanScheduleControllers {

    @Autowired
    QLKSClearScheduleService qlksClearScheduleService;

    @PostMapping(value = "/scheduling")
    public ResponseEntity<List<QLKSCleanScheduleResponse>> getClearSchedule(@RequestBody CleanScheduleRequest request) throws HotelManagerException {
        return ResponseEntity.ok(qlksClearScheduleService.getAll(request));
    }

    @PostMapping(value = "/scheduling/save")
    public ResponseEntity<BaseApiResponse> saveSchedule() throws HotelManagerException {
        qlksClearScheduleService.save();
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PostMapping(value = "/scheduling-checkIn")
    public ResponseEntity<BaseApiResponse> checkInSchedule(@RequestBody CheckCleanScheduleRequest request) throws HotelManagerException {
        qlksClearScheduleService.checkInClean(request.getIdRoom(), request.getIdEmployee());
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PostMapping(value = "/scheduling-checkOut")
    public ResponseEntity<BaseApiResponse> checkOutSchedule(@RequestBody CheckCleanScheduleRequest request) throws HotelManagerException {
        qlksClearScheduleService.checkOutClean(request.getIdRoom(), request.getIdEmployee());
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PostMapping(value = "/scheduling/log")
    public ResponseEntity<List<QLKSLogCleanScheduleResponse>> getLogClean(@RequestBody CleanScheduleRequest request) throws HotelManagerException {
        return ResponseEntity.ok(qlksClearScheduleService.getAllLog(request));
    }

}
