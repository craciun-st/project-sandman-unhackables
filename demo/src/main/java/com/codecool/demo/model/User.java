package com.codecool.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseModel {

    @Transient
    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @OneToMany(mappedBy = "userOwner")
    @JsonManagedReference
    private List<Task> tasks;
    private String email;

    @JsonIgnore
    private String passwordHash;

    @ElementCollection(targetClass = UserRole.class)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    private Set<UserRole> roles;

    public User() {}

    public User(String name) {
        super(name);
        this.tasks = new ArrayList<>();
        this.setPasswordHashFromPlainText("123");
        this.email = name + "@test.test";
        this.roles.add(UserRole.USER);
    }

    public User(Long id, String name) {
        super(id, name);
        this.tasks = new ArrayList<>();
        this.setPasswordHashFromPlainText("123");
        this.email = name + "@test.test";
        this.roles.add(UserRole.USER);
    }

    public User(Long id, String name, String email, String plainTextPassword, UserRole role) {
        super(id, name);
        this.tasks = new ArrayList<>();
        this.email = email;
        this.setPasswordHashFromPlainText(plainTextPassword);
        this.roles.add(role);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setPasswordHashFromPlainText(String plainText) {
        this.passwordHash = passwordEncoder.encode(plainText);
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public void addRole(UserRole role) {
        this.roles.add(role);
    }



}
