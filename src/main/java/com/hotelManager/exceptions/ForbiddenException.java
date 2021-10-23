package com.hotelManager.exceptions;

public class ForbiddenException extends HotelManagerException {

    public ForbiddenException(int errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}
