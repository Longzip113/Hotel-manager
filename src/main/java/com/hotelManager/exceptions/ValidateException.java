package com.hotelManager.exceptions;

public class ValidateException extends HotelManagerException {

    public ValidateException(int errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}
