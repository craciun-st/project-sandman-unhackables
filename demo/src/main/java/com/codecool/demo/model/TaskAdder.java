package com.codecool.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class TaskAdder {
    private final int userId;
    private final Task task;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public TaskAdder(int userId, Task task) {
        this.userId = userId;
        this.task = task;
    }

    public int getUserId() {
        return userId;
    }

    public Task getTask() {
        return task;
    }
}
