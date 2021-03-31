package bside.writing.controller;

import com.google.gson.JsonObject;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.io.DecodingException;
import org.codehaus.jackson.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import io.jsonwebtoken.security.*;
import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler({ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> expiredToken(Exception e){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("error_code", "token_error");
        response.put("error_desc", "expired token");
        return response;
    }

    @ExceptionHandler({
            SignatureException.class,
            MalformedJwtException.class,
            JsonParseException.class // idtoken
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> unauthorizedToken(Exception e){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("error_code", "token_error");
        response.put("error_desc", "invalid token");
        return response;
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            DecodingException.class, // jwt *^%&
            MissingRequestHeaderException.class // no header
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> illegalArgumentException(Exception e){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("error_code", "bad request");
        response.put("error_desc", e.getMessage());
        return response;
    }

    @ExceptionHandler({
        HttpMessageNotReadableException.class // bad json request
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> HttpMessageNotReadableException(Exception e){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("error_code", "bad request");
        response.put("error_desc", "Request JSON parse error");
        return response;
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> noSuchElementException(Exception e){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("error_code", "not found");
        response.put("error_desc", e.getMessage());
        return response;
    }
}
