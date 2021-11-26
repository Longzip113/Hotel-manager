package com.hotelManager.controllers;

import com.hotelManager.dtos.request.HotelDeviceRequest;
import com.hotelManager.dtos.responses.AddApiResponse;
import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.entities.QLKSHotelDeviceEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSEmployeeModel;
import com.hotelManager.services.QLKSHotelDeviceService;
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
public class HotelDeviceControllers {

    @Autowired
    QLKSHotelDeviceService qlksHotelDeviceService;

    @GetMapping(value = "/hotel-device")
    public ResponseEntity<List<QLKSHotelDeviceEntity>> getList() throws HotelManagerException {
        return ResponseEntity.ok(qlksHotelDeviceService.getAll());
    }

    @PostMapping(value = "/hotel-device")
    public ResponseEntity<AddApiResponse<QLKSHotelDeviceEntity>> add(@RequestBody @Valid HotelDeviceRequest hotelDeviceRequest) throws HotelManagerException {

        return ResponseEntity.ok(new AddApiResponse(qlksHotelDeviceService.add(hotelDeviceRequest)));
    }

    @GetMapping(value = "/hotel-device/{id}")
    public ResponseEntity<QLKSHotelDeviceEntity> getDetail(@PathVariable("id") String id) throws HotelManagerException {
        return ResponseEntity.ok(qlksHotelDeviceService.getDetail(id));
    }

    @DeleteMapping(value = "/hotel-device/{id}")
    public ResponseEntity<BaseApiResponse> delete(@PathVariable("id") String id) throws HotelManagerException {
        qlksHotelDeviceService.delete(id);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PutMapping(value = "/hotel-device/{id}")
    public ResponseEntity<BaseApiResponse> update(@PathVariable("id") String id, @RequestBody HotelDeviceRequest updateHotelDeviceRequest) throws HotelManagerException {

       qlksHotelDeviceService.update(id, updateHotelDeviceRequest);
        return ResponseEntity.ok(new BaseApiResponse());
    }
}
