package com.hotelManager.controllers;

import com.hotelManager.dtos.request.DelegationRequest;
import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.dtos.responses.QLKSDelegationResponse;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.services.QLKSDelegationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/manager")
@Slf4j
public class DelegationControllers {

    @Autowired
    QLKSDelegationService qlksDelegationService;

    @GetMapping(value = "/delegation")
    public ResponseEntity<List<QLKSDelegationResponse>> getList() throws HotelManagerException {

        return ResponseEntity.ok(qlksDelegationService.getAll());
    }

    @GetMapping(value = "/delegation/{id}")
    public ResponseEntity<QLKSDelegationResponse> getDetail(@PathVariable("id") String id) throws HotelManagerException {
        return ResponseEntity.ok(qlksDelegationService.getDetail(id));
    }

    @ApiIgnore
    @GetMapping(value = "/delegation/search")
    public ResponseEntity<BaseApiResponse> get() throws Exception {

        return ResponseEntity.ok(new BaseApiResponse());
    }

    @DeleteMapping(value = "/delegation/{id}")
    public ResponseEntity<BaseApiResponse> delete(@PathVariable("id") String id) throws HotelManagerException {
        qlksDelegationService.delete(id);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PostMapping(value = "/delegation")
    public ResponseEntity<BaseApiResponse> add(@RequestBody @Valid DelegationRequest addDelegationRequest) throws HotelManagerException {
        qlksDelegationService.add(addDelegationRequest);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PutMapping(value = "/delegation/{id}")
    public ResponseEntity<BaseApiResponse> update(@PathVariable("id") String id, @RequestBody @Valid DelegationRequest updateDelegationRequest) throws HotelManagerException {
        qlksDelegationService.update(id ,updateDelegationRequest);
        return ResponseEntity.ok(new BaseApiResponse());
    }
}
