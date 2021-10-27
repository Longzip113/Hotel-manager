package com.hotelManager.controllers;

import com.hotelManager.dtos.request.AddTypeRoomRequest;
import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.entities.QLKSTypeRoomEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.services.QLKSTypeRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/manager")
@Slf4j
public class TypeRoomControllers {

    @Autowired
    QLKSTypeRoomService qlksTypeRoomService;

    @GetMapping(value = "/type-room")
    public ResponseEntity<List<QLKSTypeRoomEntity>> getListTypeRoom() throws HotelManagerException {
        return ResponseEntity.ok(qlksTypeRoomService.getAll());
    }

    @PostMapping(value = "/type-room")
    public ResponseEntity<BaseApiResponse> addTypeRoom(@RequestBody AddTypeRoomRequest addTypeRoomRequest) throws HotelManagerException {
        qlksTypeRoomService.addTypeRoom(addTypeRoomRequest);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @DeleteMapping(value = "/type-room/{id}")
    public ResponseEntity<BaseApiResponse> deleteTypeRoom(@PathVariable("id") String id) throws HotelManagerException {
        qlksTypeRoomService.deleteTypeRoom(id);
        return ResponseEntity.ok(new BaseApiResponse());
    }
}
