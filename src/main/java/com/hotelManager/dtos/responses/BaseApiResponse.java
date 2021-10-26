package com.hotelManager.dtos.responses;

import com.hotelManager.constants.ResponseCodes;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseApiResponse {

    protected int code;
    protected String message;

    public BaseApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseApiResponse(int code) {
        this.code = code;
    }

    public BaseApiResponse() {
        this.code = ResponseCodes.SUCCESS;
        this.message = "Thành công";
    }
}

