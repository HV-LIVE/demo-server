package org.hvlive.server.demoserver.exception;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class ErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        Throwable error = getError(webRequest);
        if (error instanceof ErrorException) {
            map.put("code", ((ErrorException) error).getCode());
        }
        return map;
    }
}
