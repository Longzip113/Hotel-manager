package com.hotelManager.exceptions;

public class ExternalServiceException extends HotelManagerException {

    public ExternalServiceException(int errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}
