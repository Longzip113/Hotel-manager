package com.hotelManager.controllers;

import com.hotelManager.constants.Constants;
import com.hotelManager.dtos.request.TurnOverRequest;
import com.hotelManager.dtos.responses.QLKSInfoTurnOverResponse;
import com.hotelManager.dtos.responses.QLKSTypeRoomReponse;
import com.hotelManager.entities.QLKSCustomerEntity;
import com.hotelManager.entities.QLKSHotelDeviceEntity;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.entities.QLKSServiceEntity;
import com.hotelManager.exceptions.HotelManagerException;
import com.hotelManager.model.QLKSEmployeeModel;
import com.hotelManager.model.QLKSRoomModel;
import com.hotelManager.services.*;
import com.hotelManager.utils.WriteDataToCSV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/manager")
@Slf4j
public class ReportControllers {

    @Autowired
    QLKSBillService qlksBillService;

    @Autowired
    QLKSRoomService qlksRoomService;

    @Autowired
    QLKSRoleService qlksRoleService;

    @Autowired
    QLKSServiceService qlksServiceService;

    @Autowired
    QLKSEmployeeService qlksEmployeeService;

    @Autowired
    QLKSHotelDeviceService qlksHotelDeviceService;

    @Autowired
    QLKSTypeRoomService qlksTypeRoomService;

    @Autowired
    QLKSCustomerService qlksCustomerService;


    @GetMapping("/download/turnover")
    public void downloadCSVTurnOver(HttpServletResponse response) throws IOException, HotelManagerException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=turnover.csv");
        TurnOverRequest request = new TurnOverRequest();

        List<QLKSInfoTurnOverResponse> data = qlksBillService.getTurnOver(request);
        WriteDataToCSV.writeObjectToCSVTurnover(response.getWriter(), data);
    }

    @GetMapping("/download/room")
    public void downloadCSVRoom(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=room.csv");


        List<QLKSRoomModel> data = qlksRoomService.getAll("1", Constants.SORT_OR_DER_ASC);
        WriteDataToCSV.writeObjectToCSVListRoom(response.getWriter(), data);
    }

    @GetMapping("/download/role")
    public void downloadCSVRole(HttpServletResponse response) throws IOException, HotelManagerException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=role.csv");

        List<QLKSRoleEntity> data = qlksRoleService.getAll();
        WriteDataToCSV.writeObjectToCSVListRole(response.getWriter(), data);
    }

    @GetMapping("/download/employee")
    public void downloadCSVEmployee(HttpServletResponse response) throws IOException, HotelManagerException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=role.csv");

        List<QLKSEmployeeModel> data = qlksEmployeeService.getAll();
        WriteDataToCSV.writeObjectToCSVListEmployee(response.getWriter(), data);
    }

    @GetMapping("/download/type_room")
    public void downloadCSVTypeRoom(HttpServletResponse response) throws IOException, HotelManagerException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=typeRoom.csv");

        List<QLKSTypeRoomReponse> data = qlksTypeRoomService.getAll();
        WriteDataToCSV.writeObjectToCSVListTypeRoom(response.getWriter(), data);
    }

    @GetMapping("/download/service")
    public void downloadCSVService(HttpServletResponse response) throws IOException, HotelManagerException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=service.csv");

        List<QLKSServiceEntity> data = qlksServiceService.getAll();
        WriteDataToCSV.writeObjectToCSVListService(response.getWriter(), data);
    }

    @GetMapping("/download/device")
    public void downloadCSVDevice(HttpServletResponse response) throws IOException, HotelManagerException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=device.csv");

        List<QLKSHotelDeviceEntity> data = qlksHotelDeviceService.getAll();
        WriteDataToCSV.writeObjectToCSVListDevice(response.getWriter(), data);
    }

    @GetMapping("/download/customer")
    public void downloadCSVCustomer(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=customer.csv");

        List<QLKSCustomerEntity> data = qlksCustomerService.getAll("1", Constants.SORT_OR_DER_ASC);
        WriteDataToCSV.writeObjectToCSVListCustomer(response.getWriter(), data);
    }

}
