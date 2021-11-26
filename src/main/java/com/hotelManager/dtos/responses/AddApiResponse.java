package com.hotelManager.dtos.responses;

import com.hotelManager.constants.ResponseCodes;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddApiResponse<T> {

    protected int code;
    protected String message;
    protected T data;

    public AddApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public AddApiResponse(int code) {
        this.code = code;
    }

    public AddApiResponse(T data) {
        this.code = ResponseCodes.SUCCESS;
        this.message = "Thành công";
        this.data = data;
    }
}

