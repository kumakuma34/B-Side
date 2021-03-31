package bside.writing.controller;

import com.google.gson.JsonObject;

import io.jsonwebtoken.ExpiredJwtException;
import org.codehaus.jackson.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import io.jsonwebtoken.security.*;
import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler({ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> expiredToken(Exception e){
        Map<String, Object> response = new HashMap<>();
        response.put("error_code", "token_error");
        response.put("error_desc", "expired token");
        return response;
    }

    @ExceptionHandler({SignatureException.class, MalformedJwtException.class, JsonParseException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> unauthorizedToken(Exception e){
        Map<String, Object> response = new HashMap<>();
        response.put("error_code", "token_error");
        response.put("error_desc", "invalid token");
        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> illegalArgumentException(Exception e){
        Map<String, Object> response = new HashMap<>();
        response.put("error_code", "bad request");
        response.put("error_desc", e.getMessage());
        return response;
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> noSuchElementException(Exception e){
        Map<String, Object> response = new HashMap<>();
        response.put("error_code", "not found");
        response.put("error_desc", e.getMessage());
        return response;
    }
}
