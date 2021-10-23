package com.hotelManager.services.impl;

import com.hotelManager.constants.enums.HotelManagerResponseCode;
import com.hotelManager.exceptions.ValidateException;
import com.hotelManager.services.QLKSUser;
import com.hotelManager.utils.HotelManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class QLKSUserImpl implements QLKSUser {
    @Override
    public void addUser(Integer a) {
        System.out.println("addUser() is running " + a);
    }

    public String findUser() {
        System.out.println("findUser() is running ");
        return "abc";
    }

    /* *
     * @author: Longnt1
     * @since: 10/9/21 10:03 PM
     * @description:
     * @update:
     *      - LongNT1: 10/9/21 - 10:07 PM
     *
     * van ban 1
     * van ban 2
     * van ban 3
     * van ban 4
     * */
//    ! error
//    ? info
//    * warning
    public void userThrowException() throws Exception {
        System.out.println("userThrowException() is running ");
        if (true) {
            HotelManagerUtils.throwException(ValidateException.class, HotelManagerResponseCode.CAN_NOT_READ_FILE);
        }

        log.info("sas");
    }

}
