package com.codecool.demo.model.authentication;

import java.io.Serializable;

public class AuthRequest implements Serializable {
    private static final long serialVersionUID = -825746773077147302L;  // needed for binary version of Serializable
                                                                    // should change this when the structure of the
                                                                    // class (for example, its fields) changes
    private String username;
    private String password;


    public AuthRequest() {
        // empty constructor needed for JSON serialization
    }

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
