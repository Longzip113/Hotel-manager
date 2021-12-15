package com.hotelManager.controllers;

import com.hotelManager.dtos.responses.QLKSInfoBillResponse;
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
public class BillControllers {

    @Autowired
    QLKSBillService qlksBillService;

    @GetMapping(value = "/bill")
    public ResponseEntity<List<QLKSInfoBillResponse>> getInfoOut() throws HotelManagerException {

        return ResponseEntity.ok(qlksBillService.getAll());
    }
}
