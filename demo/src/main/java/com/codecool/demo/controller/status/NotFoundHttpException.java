package com.codecool.demo.controller.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundHttpException extends RuntimeException {
    private static final long serialVersionUID = 7866765001790753752L;
}
