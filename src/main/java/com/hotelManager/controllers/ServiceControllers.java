package com.hotelManager.controllers;

import com.hotelManager.dtos.request.ServiceRequest;
import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.entities.QLKSServiceEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.services.QLKSServiceService;
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
public class ServiceControllers {

    @Autowired
    QLKSServiceService qlksServiceService;

    @GetMapping(value = "/service")
    public ResponseEntity<List<QLKSServiceEntity>> getListService() throws HotelManagerException {
        return ResponseEntity.ok(qlksServiceService.getAll());
    }

    @PostMapping(value = "/service")
    public ResponseEntity<BaseApiResponse> addService(@RequestBody @Valid ServiceRequest addServiceRequest) throws HotelManagerException {
        qlksServiceService.addService(addServiceRequest);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @GetMapping(value = "/service/{id}")
    public ResponseEntity<QLKSServiceEntity> getDetailService(@PathVariable("id") String id) throws HotelManagerException {
        return ResponseEntity.ok(qlksServiceService.getDetailService(id));
    }

    @DeleteMapping(value = "/service/{id}")
    public ResponseEntity<BaseApiResponse> deleteService(@PathVariable("id") String id) throws HotelManagerException {
        qlksServiceService.deleteService(id);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PutMapping(value = "/service/{id}")
    public ResponseEntity<BaseApiResponse> updateService(@PathVariable("id") String id, @RequestBody ServiceRequest updateServiceRequest) throws HotelManagerException {

        if (StringUtils.isNotBlank(updateServiceRequest.getNameService()) || updateServiceRequest.getPrice() != null) {
            qlksServiceService.updateService(id, updateServiceRequest);
        }

        return ResponseEntity.ok(new BaseApiResponse());
    }
}
