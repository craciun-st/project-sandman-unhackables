package com.codecool.demo.controller.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestHttpException extends RuntimeException {
    // IDE assigned value, doesn't matter if you aren't serializing objects, but probably required internally
    // it is important that different classes have different serialversionUID
    private static final long serialVersionUID = -438118799460446462L;




}
