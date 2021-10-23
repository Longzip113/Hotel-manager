package com.hotelManager.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ValidateExceptionFileExisted extends Exception {

    private List<String> fileNameExisted;

    private int errorCode;

    public ValidateExceptionFileExisted(int errorCode, String message, List<String> fileNameExisted){
        super(message);
        this.errorCode = errorCode;
        this.fileNameExisted = fileNameExisted;
    }

}
