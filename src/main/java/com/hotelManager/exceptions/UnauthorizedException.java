package com.hotelManager.exceptions;

public class UnauthorizedException extends HotelManagerException {

    public UnauthorizedException(int errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}
