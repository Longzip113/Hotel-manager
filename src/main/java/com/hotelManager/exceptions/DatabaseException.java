package com.hotelManager.exceptions;

public class DatabaseException extends HotelManagerException {

    public DatabaseException(int errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
}
