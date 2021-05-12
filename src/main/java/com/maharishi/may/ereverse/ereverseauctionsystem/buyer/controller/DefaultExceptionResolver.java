package com.maharishi.may.ereverse.ereverseauctionsystem.buyer.controller;

import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DefaultExceptionResolver
{
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MalformedObjectException.class)
    public Map<String,String> handleMalformedUserException(MalformedObjectException exception)
    {
        Map<String,String> result=new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(objectError ->
        {
            result.put(((FieldError)objectError).getField(),objectError.getDefaultMessage());
        });
        return result;
    }
}
