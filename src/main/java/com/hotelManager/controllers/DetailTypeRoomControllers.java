package com.hotelManager.controllers;

import com.hotelManager.dtos.request.DetailTypeRoomRequest;
import com.hotelManager.dtos.request.RoleRequest;
import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.services.QLKSDetailTypeRoomService;
import com.hotelManager.services.QLKSRoleService;
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
public class DetailTypeRoomControllers {

    @Autowired
    QLKSDetailTypeRoomService qlksDetailTypeRoomService;

    @PostMapping(value = "/detail-type")
    public ResponseEntity<BaseApiResponse> add(@RequestBody @Valid DetailTypeRoomRequest request)
            throws HotelManagerException {

        qlksDetailTypeRoomService.add(request);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @DeleteMapping(value = "/detail-type/{id}")
    public ResponseEntity<BaseApiResponse> delete(@PathVariable("id") String id)
            throws HotelManagerException {

        qlksDetailTypeRoomService.delete(id);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PutMapping(value = "/detail-type/{id}")
    public ResponseEntity<BaseApiResponse> update(@PathVariable("id") String id,
                                                  @RequestBody DetailTypeRoomRequest request)
            throws HotelManagerException {

        qlksDetailTypeRoomService.update(id, request);
        return ResponseEntity.ok(new BaseApiResponse());
    }
}
