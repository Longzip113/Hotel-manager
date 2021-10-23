package com.hotelManager.exceptions;

public class HotelManagerException extends Exception {
    protected int errorCode;

    protected String message;

    public HotelManagerException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public HotelManagerException(String message) {
        super(message);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


}
