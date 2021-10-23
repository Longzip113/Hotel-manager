package com.hotelManager.controllers;

import com.hotelManager.services.QLKSUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/manager")
@Slf4j
public class RoomControllers {

    @Autowired
    QLKSUser qlksUser;

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

    @PostMapping(value = "/room")
    public ResponseEntity<String> addRoom() throws Exception {

        return ResponseEntity.ok("Success !!");
    }

    @PutMapping(value = "/room")
    public ResponseEntity<String> updateRoom() throws Exception {

        return ResponseEntity.ok("Success !!");
    }
}
