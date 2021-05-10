package com.codecool.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Task extends BaseModel {
    public Task(int id, String name) {
        super(id, name);
    }




}
