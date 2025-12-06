package com.example.stock_manager.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static <T> ResponseEntity<T> generateResponse(
            HttpStatus status,
            Boolean error,
            String code,
            T responseObj
    ) {
        Map<String, Object> map = new HashMap();
        map.put("error", error);
        map.put("errorCode", code);
        map.put("data", responseObj);
        return new ResponseEntity<T>((T) map, status);
    }

    public static ResponseEntity<Object> generateResponseMessage(
            HttpStatus status,
            String message,
            Boolean error,
            String code
    ) {
        Map<String, Object> map = new HashMap();
        map.put("error", error);
        map.put("errorCode", code);

        Map<String, Object> mapData = new HashMap();
        mapData.put("message", message);
        map.put("data", mapData);

        return new ResponseEntity<Object>(map, status);
    }

    public static ResponseEntity<Object> generateResponseError(
            HttpStatus status,
            Boolean error,
            String errorCode,
            ValidationErrorResponse errors
    ) {
        Map<String, Object> map = new HashMap();

        map.put("error", error);
        map.put("errorCode", errorCode);
        map.put("data", errors);

        return new ResponseEntity<Object>(map, status);
    }

    public static ResponseEntity<Object> generateResponseError2(
            HttpStatus status,
            Boolean error,
            String errorCode,
            Map<String, Object> mapErrors
    ) {
        Map<String, Object> map = new HashMap();

        map.put("error", error);
        map.put("errorCode", errorCode);

        Map<String, Object> mapError = new HashMap();
        mapError.put("errors", mapErrors);
        map.put("data", mapErrors);

        return new ResponseEntity<Object>(map, status);
    }
}
