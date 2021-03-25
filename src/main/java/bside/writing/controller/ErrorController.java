package bside.writing.controller;

import com.google.gson.JsonObject;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import io.jsonwebtoken.security.*;
import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
@CrossOrigin
public class ErrorController {

    @ExceptionHandler({SignatureException.class, MalformedJwtException.class, AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unauthorizedToken(Exception e){
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("error_msg", "invalid token");
        return jsonResponse.toString();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illergalArguException(Exception e){
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("error_msg", e.getMessage());
        return jsonResponse.toString();
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String noSuchElementException(Exception e){
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("error_msg", e.getMessage());
        return jsonResponse.toString();
    }
}
