package bside.writing.controller;

import bside.writing.templateClass.StatusCode;
import com.google.api.client.json.Json;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.*;
import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler({SignatureException.class, MalformedJwtException.class})
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
