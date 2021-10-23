package com.hotelManager.services.impl;

import com.hotelManager.services.LoggingService;
import com.hotelManager.utils.GsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoggingServiceImpl implements LoggingService {

    private static Logger logger = LoggerFactory.getLogger(LoggingServiceImpl.class);

    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Method action, Object body) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(httpServletRequest, ContentCachingRequestWrapper.class);

        Map<String, String> parameters = buildParametersMap(httpServletRequest);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("REQUEST ");
        stringBuilder.append("Id=[").append(httpServletRequest.getAttribute("Request-Id")).append("] ");
        stringBuilder.append("Method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("Path=[").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("Headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");

        if (!parameters.isEmpty()) {
            stringBuilder.append("Parameters=[").append(parameters).append("] ");
        }

//        String responseBodyStr = getRequestBody(wrapper);
//
//        if (responseBodyStr != null) {
//            stringBuilder.append("body=[" + GsonHelper.defaultInstance().toJson(responseBodyStr) + "]");
//        }

        logger.info(stringBuilder.toString());
    }

    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Method action, Object body) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("RESPONSE ");
        stringBuilder.append("Id=[").append(httpServletRequest.getAttribute("Request-Id")).append("] ");
        stringBuilder.append("Method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("Path=[").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("ResponseHeaders=[").append(buildHeadersMap(httpServletResponse)).append("] ");

        String responseBodyStr = getResponseBody(body);
        stringBuilder.append("ResponseBody=[").append(responseBodyStr).append("] ");

        logger.info(stringBuilder.toString());
    }

    @Override
    public void logErrorRequest(HttpServletRequest httpServletRequest, Object body) {

        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> parameters = buildParametersMap(httpServletRequest);

        stringBuilder.append("REQUEST ");
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");

        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }

        logger.info(stringBuilder.toString());
    }

    @Override
    public void logErrorResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("RESPONSE ");
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("responseHeaders=[").append(buildHeadersMap(httpServletResponse)).append("] ");
        stringBuilder.append("responseBody=[").append(GsonHelper.defaultInstance().toJson(body)).append("] ");

        logger.info(stringBuilder.toString());
    }

    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }

        return resultMap;
    }

    private Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

    /**
     *
     * @param response
     * @return
     */
    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();

        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }

        return map;
    }

    /**
     * Print request parameters
     *
     * @param request
     */
    private String getRequestBody(ContentCachingRequestWrapper request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if(wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if(buf.length > 0) {
                String payload;
                try {
                    payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                    payload = "[unknown]";
                }
                return payload.replaceAll("\\n","");
            }
        }
        return "";
    }

    /**
     * Print return parameters
     *
     * @param object
     */
    private String getResponseBody(Object object) {
        if (object == null) {
            return "";
        }

        return GsonHelper.defaultInstance().toJson(object);
    }
}
