package com.hotelManager.filters;

import com.hotelManager.services.LoggingService;
import com.hotelManager.services.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Random;

@Component
public class LogInterceptor implements HandlerInterceptor {
    @Autowired
    LoggingService loggingService;

    static Random random = new Random();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())) {
            request.setAttribute("Request-Id", createRequestId());
            loggingService.logRequest(request, getAction(handler), null);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
//        loggingService.logResponse(request, response, getAction(o), null);
    }

    public Method getAction(Object handler) {

        if (handler instanceof HandlerMethod) {
            return ((HandlerMethod) handler).getMethod();
        }

        return null;
    }

    int createRequestId() {
        return random.nextInt(10000);
    }
}
