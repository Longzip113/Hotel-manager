package com.hotelManager.configurations;

import com.hotelManager.constants.ResponseCodes;
import com.hotelManager.dtos.responses.ErrorApiResponse;
import com.hotelManager.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.IOException;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(ValidateException.class)
    public @ResponseBody
    ResponseEntity<ErrorApiResponse> handleValidateException(ValidateException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorApiResponse(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public @ResponseBody
    ResponseEntity<ErrorApiResponse> handleValidateException(ForbiddenException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorApiResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage()));
    }

    @ExceptionHandler(DatabaseException.class)
    public @ResponseBody
    ResponseEntity<ErrorApiResponse> handleDatabaseException(DatabaseException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorApiResponse(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler({ExternalServiceException.class, IOException.class})
    public @ResponseBody
    ResponseEntity<ErrorApiResponse> handleExternalServiceException(ExternalServiceException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorApiResponse(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public @ResponseBody
    ResponseEntity<ErrorApiResponse> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorApiResponse(ResponseCodes.UNAUTHORIZED, ex.getMessage() != null ? ex.getMessage() : "UNAUTHORIZED"));
    }

    @ExceptionHandler(BindException.class)
    public @ResponseBody
    ResponseEntity<ErrorApiResponse> handledMethodArgumentNotValidException(BindException ex) {
        String message = ex.getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorApiResponse(ResponseCodes.PARAMETER_INVALID, message != null ? message : "Parameter invalid"));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @Deprecated
    public @ResponseBody
    ResponseEntity<ErrorApiResponse> handleFileSizeLimitExceeded(MaxUploadSizeExceededException ex) {
        return new ResponseEntity<>(new ErrorApiResponse(413, "Request entity too large"), HttpStatus.REQUEST_ENTITY_TOO_LARGE);
    }

}
