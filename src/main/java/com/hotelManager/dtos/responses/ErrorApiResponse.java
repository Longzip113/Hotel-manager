package com.hotelManager.dtos.responses;

public class ErrorApiResponse extends BaseApiResponse {
    public ErrorApiResponse(int code, String message) {
        super(code, message);
    }
}
