package com.codecool.demo.model;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends BaseModel {

    @OneToMany(mappedBy = "userOwner")
    private List<Task> tasks;

    public User() {}

    public User(String name) {
        super(name);
        this.tasks = new ArrayList<>();
    }

    public User(Long id, String name) {
        super(id, name);
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        task.setUserOwner(this);
        this.tasks.add(task);
    }

    public void clearTasks() {
        this.tasks.clear();
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

}
