package com.hotelManager.constants.enums;

import lombok.Getter;

@Getter
public enum HotelManagerResponseCode {
    SUCCESS                             (1,     "Success"),
    UNAUTHORIZED                        (400,   "Unauthorized"),
    FORBIDDEN                           (403,   "You don't have permission"),
    ERROR_SERVER                        (500,   "Server error"),
    ERROR_PASSWORD_EMAIL                (-1,    "Email address or password is incorrect."),
    ERROR_DELETE_TYPE_ROOM              (-2,    "Có phòng đang chứa loại phòng xin kiểm tra lại !"),
    ERROR_CHECK_ROOM                    (-3,    "Phòng không tồn tại !");

    private final int responseCode;

    private final String message;

    HotelManagerResponseCode(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }
}
