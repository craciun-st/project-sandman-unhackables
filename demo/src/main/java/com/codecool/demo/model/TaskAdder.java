package com.codecool.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class TaskAdder {
    private final long userId;
    private final Task task;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public TaskAdder(
            long userId,
            Task task) {
        this.userId = userId;
        this.task = task;
    }

    public long getUserId() {
        return userId;
    }

    public Task getTask() {
        return task;
    }
}
