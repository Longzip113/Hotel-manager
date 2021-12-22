package com.hotelManager.controllers;

import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.dtos.responses.QLKSInfoBillResponse;
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

    @GetMapping(value = "/scheduling")
    public ResponseEntity<List<QLKSInfoBillResponse>> getInfoOut() throws HotelManagerException {

        return ResponseEntity.ok(null);
    }

    @PostMapping(value = "/scheduling")
    public ResponseEntity<BaseApiResponse> addSchedule(@RequestBody Long dayWorking) throws HotelManagerException {
        qlksClearScheduleService.save(dayWorking);
        return ResponseEntity.ok(new BaseApiResponse());
    }
}
