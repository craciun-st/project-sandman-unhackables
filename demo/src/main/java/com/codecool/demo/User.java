package com.codecool.demo;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseModel {
    private List<Task> tasks;


    public User(int id, String name) {
        super(id, name);
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void clearTasks() {
        this.tasks.clear();
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

}
