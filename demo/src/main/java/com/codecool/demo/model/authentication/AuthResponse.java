package com.codecool.demo.model.authentication;

import java.io.Serializable;

public class AuthResponse implements Serializable {
    private static final long serialVersionUID = 3798081137608239470L;  // for binary version of Serializable

    private final String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
