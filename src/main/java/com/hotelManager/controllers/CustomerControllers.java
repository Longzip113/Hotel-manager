package com.hotelManager.controllers;

import com.hotelManager.constants.Constants;
import com.hotelManager.dtos.request.CustomerRequest;
import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.entities.QLKSCustomerEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.services.QLKSCustomerService;
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
public class CustomerControllers {

    @Autowired
    QLKSCustomerService qlksCustomerService;

    @GetMapping(value = "/customer")
    public ResponseEntity<List<QLKSCustomerEntity>> getListCustomer(
            @RequestParam(defaultValue = "1") String sortBy,
            @RequestParam(defaultValue = Constants.SORT_OR_DER_ASC) String sortOrder) {

        return ResponseEntity.ok(qlksCustomerService.getAll(sortBy, sortOrder));
    }

    @GetMapping(value = "/customer/{id}")
    public ResponseEntity<QLKSCustomerEntity> getListCustomer(@PathVariable("id") String id) throws HotelManagerException {
        return ResponseEntity.ok(qlksCustomerService.getDetail(id));
    }

    @ApiIgnore
    @GetMapping(value = "/customer/search")
    public ResponseEntity<String> getCustomer() throws Exception {

        return ResponseEntity.ok("Success !!");
    }

    @DeleteMapping(value = "/customer/{id}")
    public ResponseEntity<BaseApiResponse> deleteCustomer(@PathVariable("id") String id) throws HotelManagerException {
        qlksCustomerService.delete(id);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PostMapping(value = "/customer")
    public ResponseEntity<BaseApiResponse> addCustomer(@RequestBody @Valid CustomerRequest customerRequest) throws HotelManagerException {
        qlksCustomerService.save(customerRequest);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PutMapping(value = "/customer/{id}")
    public ResponseEntity<BaseApiResponse> updateCustomer(@PathVariable("id") String id, @RequestBody @Valid CustomerRequest customerRequest) throws HotelManagerException {
        qlksCustomerService.update(customerRequest, id);
        return ResponseEntity.ok(new BaseApiResponse());
    }
}
