package com.hotelManager.controllers;

import com.hotelManager.dtos.request.RoleRequest;
import com.hotelManager.dtos.responses.BaseApiResponse;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.exceptions.HotelManagerException;
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
public class RoleControllers {

    @Autowired
    QLKSRoleService qlksRoleService;

    @GetMapping(value = "/role")
    public ResponseEntity<List<QLKSRoleEntity>> getList() throws HotelManagerException {
        return ResponseEntity.ok(qlksRoleService.getAll());
    }

    @PostMapping(value = "/role")
    public ResponseEntity<BaseApiResponse> add(@RequestBody @Valid RoleRequest roleRequest) throws HotelManagerException {
        qlksRoleService.add(roleRequest);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @GetMapping(value = "/role/{id}")
    public ResponseEntity<QLKSRoleEntity> getDetail(@PathVariable("id") String id) throws HotelManagerException {
        return ResponseEntity.ok(qlksRoleService.getDetail(id));
    }

    @DeleteMapping(value = "/role/{id}")
    public ResponseEntity<BaseApiResponse> delete(@PathVariable("id") String id) throws HotelManagerException {
        qlksRoleService.delete(id);
        return ResponseEntity.ok(new BaseApiResponse());
    }

    @PutMapping(value = "/role/{id}")
    public ResponseEntity<BaseApiResponse> update(@PathVariable("id") String id, @RequestBody RoleRequest roleRequest) throws HotelManagerException {

        qlksRoleService.update(id, roleRequest);
        return ResponseEntity.ok(new BaseApiResponse());
    }
}
