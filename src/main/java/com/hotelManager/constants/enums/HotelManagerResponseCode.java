package com.hotelManager.constants.enums;

import lombok.Getter;

@Getter
public enum HotelManagerResponseCode {
    SUCCESS                             (1,     "Success"),
    UNAUTHORIZED                        (400,   "Unauthorized"),
    FORBIDDEN                           (403,   "You don't have permission"),
    ERROR_SERVER                        (500,   "Server error"),
    ERROR_PASSWORD_EMAIL                (-1,    "Email address or password is incorrect.");

    private final int responseCode;

    private final String message;

    HotelManagerResponseCode(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }
}
