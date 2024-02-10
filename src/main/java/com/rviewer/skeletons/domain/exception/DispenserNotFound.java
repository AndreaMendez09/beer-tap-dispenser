package com.rviewer.skeletons.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DispenserNotFound extends RuntimeException{
    public DispenserNotFound(){
        super("Requested dispenser does not exist");
    }
}