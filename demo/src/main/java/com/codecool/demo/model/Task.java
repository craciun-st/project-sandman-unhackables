package com.codecool.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Task extends BaseModel {
    @Min(1)
    @Max(5)
    private int importance;

    @NotNull
    private String category;

    @Column(name="done_status")
    private boolean isDone;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User userOwner;


    public Task() {

    }

    public Task(Long id, String name, int importance, String category, boolean isDone, User userOwner) {
        super(id, name);
        this.importance = importance;
        this.category = category;
        this.isDone = isDone;
        this.userOwner = userOwner;
    }

    /**
     * Creates task without user
     * @param id
     * @param name
     */
    public Task(Long id, String name) {
        super(id, name);
        this.importance = 3;
        this.category = "General";
        this.isDone = false;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public User getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }
}
