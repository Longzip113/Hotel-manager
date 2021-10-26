package com.hotelManager.controllers;

import com.hotelManager.constants.ResponseCodes;
import com.hotelManager.dtos.request.AddRoomRequest;
import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.exceptions.ValidateException;
import com.hotelManager.services.QLKSRoomService;
import com.hotelManager.services.QLKSTypeRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/manager")
@Slf4j
public class RoomControllers {

    @Autowired
    QLKSRoomService qlksRoomService;

    @Autowired
    QLKSTypeRoomService qlksTypeRoomService;


    @GetMapping(value = "/room")
    public ResponseEntity<String> getListRoom() throws Exception {

        return ResponseEntity.ok("Success !!");
    }

    @GetMapping(value = "/room/search")
    public ResponseEntity<String> getRoom() throws Exception {

        return ResponseEntity.ok("Success !!");
    }

    @DeleteMapping(value = "/room")
    public ResponseEntity<String> deleteRoom() throws Exception {

        return ResponseEntity.ok("Success !!");
    }

    @PostMapping(value = "/add-room")
    public ResponseEntity<BaseApiResponse> addRoom(@RequestBody @Valid AddRoomRequest addRoomRequest, Errors errors) throws HotelManagerException {
        if (errors.hasErrors()) {
            throw new ValidateException(ResponseCodes.PARAMETER_INVALID, errors.getFieldError().getDefaultMessage());
        }

        qlksRoomService.save(addRoomRequest);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PutMapping(value = "/room")
    public ResponseEntity<String> updateRoom() throws Exception {

        return ResponseEntity.ok("Success !!");
    }

    @GetMapping(value = "/list-type-room")
    public ResponseEntity<List<QLKSTypeRoomEntity>> getListTypeRoom() throws HotelManagerException {
        return ResponseEntity.ok(qlksTypeRoomService.getAll());
    }
}
