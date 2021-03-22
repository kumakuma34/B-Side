package bside.writing.controller;

import bside.writing.templateClass.StatusCode;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String illergalArguException(Exception e){
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("error_msg", e.getMessage());
        return jsonResponse.toString();
    }
}
