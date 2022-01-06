package com.hotelManager.utils;


import com.hotelManager.dtos.responses.QLKSInfoTurnOverResponse;
import com.hotelManager.dtos.responses.QLKSTypeRoomReponse;
import com.hotelManager.entities.QLKSCustomerEntity;
import com.hotelManager.entities.QLKSHotelDeviceEntity;
import com.hotelManager.entities.QLKSRoleEntity;
import com.hotelManager.entities.QLKSServiceEntity;
import com.hotelManager.model.QLKSEmployeeModel;
import com.hotelManager.model.QLKSRoomModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class WriteDataToCSV {

    public static void writeObjectToCSVTurnover(PrintWriter writer, List<QLKSInfoTurnOverResponse> datas) {
        writer.write('\ufeff');
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL
                        .withHeader("Room", "DayCheckIn", "DayCheckOut", "DayOfPayment", "RoomRent", "ServiceFee", "TotalPrice"));
        ) {
            for (QLKSInfoTurnOverResponse item : datas) {

                List<String> data = Arrays.asList(
                        item.getNameRoom(),
                        DateTimeUtils.convertDate(item.getDayCheckIn()),
                        DateTimeUtils.convertDate(item.getDayCheckOut()),
                        DateTimeUtils.convertDate(item.getDayOfPayment()),
                        item.getRoomRent().toString(),
                        item.getServiceFee().toString(),
                        item.getTotalPrice().toString()
                );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            log.error("Writing CSV error!");
            e.printStackTrace();
        }
    }

    public static void writeObjectToCSVListRoom(PrintWriter writer, List<QLKSRoomModel> datas) {
        writer.write("UTF-16LE");
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("NameRoom", "nameTypeRoom", "nameEmployee"));
        ) {
            for (QLKSRoomModel item : datas) {

                List<String> data = Arrays.asList(
                        item.getNameRoom(),
                        item.getNameTypeRoom(),
                        item.getNameEmployee()
                );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            log.error("Writing CSV error!");
            e.printStackTrace();
        }
    }

    public static void writeObjectToCSVListTypeRoom(PrintWriter writer, List<QLKSTypeRoomReponse> datas) {
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("NameTypeRoom", "Price", "Description"));
        ) {
            for (QLKSTypeRoomReponse item : datas) {

                List<String> data = Arrays.asList(
                        item.getNameTypeRoom(),
                        item.getPrice().toString(),
                        item.getDescription()
                );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            log.error("Writing CSV error!");
            e.printStackTrace();
        }
    }

    public static void writeObjectToCSVListEmployee(PrintWriter writer, List<QLKSEmployeeModel> datas) {
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("nameEmployee", "gender", "identityCard", "address", "phoneNumber", "email", "nameRole"));
        ) {
            for (QLKSEmployeeModel item : datas) {

                List<String> data = Arrays.asList(
                        item.getNameEmployee(),
                        item.getGender(),
                        item.getIdentityCard(),
                        item.getAddress(),
                        item.getPhoneNumber(),
                        item.getEmail(),
                        item.getNameRole()
                );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            log.error("Writing CSV error!");
            e.printStackTrace();
        }
    }

    public static void writeObjectToCSVListCustomer(PrintWriter writer, List<QLKSCustomerEntity> datas) {
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("name", "nationality", "phone", "email"));
        ) {
            for (QLKSCustomerEntity item : datas) {

                List<String> data = Arrays.asList(
                        item.getName(),
                        item.getNationality(),
                        item.getPhone(),
                        item.getEmail()
                );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            log.error("Writing CSV error!");
            e.printStackTrace();
        }
    }

    public static void writeObjectToCSVListService(PrintWriter writer, List<QLKSServiceEntity> datas) {
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("nameService", "price"));
        ) {
            for (QLKSServiceEntity item : datas) {

                List<String> data = Arrays.asList(
                        item.getNameService(),
                        item.getPrice().toString()
                );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            log.error("Writing CSV error!");
            e.printStackTrace();
        }
    }

    public static void writeObjectToCSVListDevice(PrintWriter writer, List<QLKSHotelDeviceEntity> datas) {
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("nameHotelDevice", "price", "quantity"));
        ) {
            for (QLKSHotelDeviceEntity item : datas) {

                List<String> data = Arrays.asList(
                        item.getNameHotelDevice(),
                        item.getPrice().toString(),
                        item.getQuantity().toString()
                );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            log.error("Writing CSV error!");
            e.printStackTrace();
        }
    }

    public static void writeObjectToCSVListRole(PrintWriter writer, List<QLKSRoleEntity> datas) {
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("nameRole", "codeRole"));
        ) {
            for (QLKSRoleEntity item : datas) {

                List<String> data = Arrays.asList(
                        item.getNameRole(),
                        item.getCodeRole()
                );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            log.error("Writing CSV error!");
            e.printStackTrace();
        }
    }
}

