package com.hotelManager.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public interface LoggingService {

    void logRequest(HttpServletRequest httpServletRequest, Method action, Object body);

    void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Method action, Object body);

    void logErrorRequest(HttpServletRequest httpServletRequest, Object body);

    void logErrorResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body);
}
