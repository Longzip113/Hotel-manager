package com.hotelManager.controllers;

import com.hotelManager.dtos.request.TurnOverRequest;
import com.hotelManager.dtos.responses.QLKSInfoTurnOverResponse;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.services.QLKSBillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/manager")
@Slf4j
public class TurnoverControllers {

    @Autowired
    QLKSBillService qlksBillService;

    @PostMapping(value = "/turnover")
    public ResponseEntity<List<QLKSInfoTurnOverResponse>> getInfoTurnOver(@RequestBody TurnOverRequest turnOverRequest) throws HotelManagerException {

        return ResponseEntity.ok(qlksBillService.getTurnOver(turnOverRequest));

    }
}
