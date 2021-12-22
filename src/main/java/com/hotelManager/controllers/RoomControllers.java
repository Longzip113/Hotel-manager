package com.hotelManager.controllers;

import com.hotelManager.constants.Constants;
import com.hotelManager.dtos.request.RoomRequest;
import com.hotelManager.dtos.request.SearchRoomRequest;
import com.hotelManager.dtos.responses.AddApiResponse;
import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSRoomModel;
import com.hotelManager.services.QLKSRoomService;
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
public class RoomControllers {

    @Autowired
    QLKSRoomService qlksRoomService;

    @GetMapping(value = "/room")
    public ResponseEntity<List<QLKSRoomModel>> getListRoom(
            @RequestParam(defaultValue = "1") String sortBy,
            @RequestParam(defaultValue = Constants.SORT_OR_DER_ASC) String sortOrder) throws Exception {

        return ResponseEntity.ok(qlksRoomService.getAll(sortBy, sortOrder));
    }

    @GetMapping(value = "/room/{id}")
    public ResponseEntity<QLKSRoomModel> getRoomDetail(@PathVariable("id") String id) throws Exception {

        return ResponseEntity.ok(qlksRoomService.getDetailRoom(id));
    }

    @PostMapping(value = "/room/search")
    public ResponseEntity<List<QLKSRoomModel>> getRoom(@RequestBody SearchRoomRequest roomRequest) {

        return ResponseEntity.ok(qlksRoomService.searchRoom(roomRequest));
    }

    @DeleteMapping(value = "/room/{id}")
    public ResponseEntity<BaseApiResponse> deleteRoom(@PathVariable("id") String id) throws HotelManagerException {
        qlksRoomService.deleteRoom(id);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PostMapping(value = "/room")
    public ResponseEntity<AddApiResponse<QLKSRoomModel>> addRoom(@RequestBody @Valid RoomRequest addRoomRequest) throws HotelManagerException {

        return ResponseEntity.ok(new AddApiResponse(qlksRoomService.save(addRoomRequest)));
    }

    @PutMapping(value = "/room/{idRoom}")
    public ResponseEntity<BaseApiResponse> updateRoom(@PathVariable("idRoom") String idRoom, @RequestBody @Valid RoomRequest roomRequest) throws HotelManagerException {

        qlksRoomService.update(roomRequest, idRoom);
        return ResponseEntity.ok(new BaseApiResponse());
    }
}
