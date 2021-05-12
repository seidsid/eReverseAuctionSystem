package com.maharishi.may.ereverse.ereverseauctionsystem.buyer.controller;

import org.springframework.validation.BindingResult;

public class MalformedObjectException extends RuntimeException{
    private BindingResult bindingResult;
    public MalformedObjectException(BindingResult bindingResult)
    {
        this(null,bindingResult);
    }
    public MalformedObjectException(String string, BindingResult bindingResult)
    {
        super(string);
        this.bindingResult=bindingResult;
    }
    public BindingResult getBindingResult()
    {
        return bindingResult;
    }
}
