package com.hotelManager.controllers;

import com.hotelManager.dtos.request.TypeRoomRequest;
import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.dtos.responses.QLKSTypeRoomReponse;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.services.QLKSTypeRoomService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/manager")
@Slf4j
public class TypeRoomControllers {

    @Autowired
    QLKSTypeRoomService qlksTypeRoomService;

    @GetMapping(value = "/type-room")
    public ResponseEntity<List<QLKSTypeRoomReponse>> getListTypeRoom() throws HotelManagerException {
        return ResponseEntity.ok(qlksTypeRoomService.getAll());
    }

    @PostMapping(value = "/type-room")
    public ResponseEntity<BaseApiResponse> addTypeRoom(@RequestBody @Valid TypeRoomRequest typeRoomRequest) throws HotelManagerException {
        qlksTypeRoomService.addTypeRoom(typeRoomRequest);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @GetMapping(value = "/type-room/{id}")
    public ResponseEntity<QLKSTypeRoomReponse> getDetailTypeRoom(@PathVariable("id") String id) throws HotelManagerException {
        return ResponseEntity.ok(qlksTypeRoomService.getDetailTypeRoom(id));
    }

    @DeleteMapping(value = "/type-room/{id}")
    public ResponseEntity<BaseApiResponse> deleteTypeRoom(@PathVariable("id") String id) throws HotelManagerException {
        qlksTypeRoomService.deleteTypeRoom(id);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PutMapping(value = "/type-room/{id}")
    public ResponseEntity<BaseApiResponse> updateTypeRoom(@PathVariable("id") String id, @RequestBody TypeRoomRequest typeRoomRequest) throws HotelManagerException {

        if (StringUtils.isNotBlank(typeRoomRequest.getNameTypeRoom()) || typeRoomRequest.getPrice() != null|| typeRoomRequest.getDescription() != null) {
            qlksTypeRoomService.updateTypeRoom(id, typeRoomRequest);
        }

        return ResponseEntity.ok(new BaseApiResponse());
    }
}
