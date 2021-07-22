package com.codecool.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;



    @OneToMany(mappedBy = "userOwner")
    @JsonManagedReference
    private List<Task> tasks;

    @ManyToMany(mappedBy = "subscribedUsers")
    @JsonBackReference
    private Set<Event> eventsJoined;

    private String email;

    @JsonIgnore     // if ever user data will be part of a JSON serialization
    private String password;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    private Set<UserRole> roles;

    public User() {}

    public User(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();

        this.email = name + "@test.test";
        this.roles = new HashSet<>();
        this.roles.add(UserRole.USER);
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = new ArrayList<>();

        this.email = name + "@test.test";
        this.roles = new HashSet<>();
        this.roles.add(UserRole.USER);

        this.eventsJoined = new HashSet<>();
    }

    public User(Long id, String name, String email, String hashedPassword, UserRole role) {
        this.id = id;
        this.name = name;
        this.tasks = new ArrayList<>();
        this.email = email;
        this.password = hashedPassword;
        this.roles = new HashSet<>();
        this.roles.add(role);
        this.eventsJoined = new HashSet<>();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordHash) {
        this.password = passwordHash;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Event> getEventsJoined() {
        return new HashSet<>(eventsJoined);
    }

    public void setEventsJoined(Set<Event> eventsJoined) {
        for (Event event : eventsJoined) {
            event.addSubscribedUser(this);
        }
        this.eventsJoined = eventsJoined;
    }

    public void joinEvent(Event event) {
        event.addSubscribedUser(this);
        this.eventsJoined.add(event);
    }

    public void leaveEvent(Event event) {
        event.removeSubscribedUser(this);
        this.eventsJoined.remove(event);
    }
}
