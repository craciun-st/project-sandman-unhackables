package com.codecool.demo.controller.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorHttpException extends RuntimeException {

    private static final long serialVersionUID = 3365103322536121524L;
}
