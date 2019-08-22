package com.resource.service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = AccessDeniedException.class)
    public Map accessDeniedHandler(AccessDeniedException ex) {
        log.error("accessDeniedHandler:{}", new Object[]{ex.getStackTrace()});
        Map<String,String> map = new HashMap<>();
        map.put("accessDeniedException", ex.getMessage());
        return map;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public Map defaultExceptionHandler(Exception ex) {
        log.error("defaultExceptionHandler:{}", new Object[]{ex.getStackTrace()});
        Map<String,String> map = new HashMap<>();
        map.put("exception", ex.getMessage());
        return map;
    }
}
