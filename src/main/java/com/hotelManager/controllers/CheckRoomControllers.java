package com.hotelManager.controllers;

import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.services.QLKSRoomArrangementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/manager")
@Slf4j
public class CheckRoomControllers {

    @Autowired
    QLKSRoomArrangementService qlksRoomArrangementService;

    @PostMapping(value = "/check-in/{id}")
    public ResponseEntity<BaseApiResponse> checkIn(@PathVariable("id") String id) throws HotelManagerException {
        qlksRoomArrangementService.checkIn(id);

        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PostMapping(value = "/check-out/{id}")
    public ResponseEntity<BaseApiResponse> checkOut(@PathVariable("id") String id) throws HotelManagerException {

        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PostMapping(value = "/cancel/{id}")
    public ResponseEntity<BaseApiResponse> cancel(@PathVariable("id") String id) throws HotelManagerException {

        return ResponseEntity.ok(new BaseApiResponse());
    }
}
