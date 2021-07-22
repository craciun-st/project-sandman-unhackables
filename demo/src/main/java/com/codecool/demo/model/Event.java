package com.codecool.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String info;

    @ManyToMany
    @JoinTable(
            name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> subscribedUsers;

    private int rewardValue;

    public Event(Long id, String name, String info, Set<User> subscribedUsers, int rewardValue) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.subscribedUsers = subscribedUsers;
        this.rewardValue = rewardValue;
    }

    public Event(Long id, String name, String info, int rewardValue) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.subscribedUsers = new HashSet<>();
        this.rewardValue = rewardValue;
    }

    public Event() {
        this.subscribedUsers = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public Set<User> getSubscribedUsers() {
        return new HashSet<>(subscribedUsers);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setSubscribedUsers(Set<User> subscribedUsers) {
        this.subscribedUsers = subscribedUsers;
    }

    public int getRewardValue() {
        return rewardValue;
    }

    public void setRewardValue(int rewardValue) {
        this.rewardValue = rewardValue;
    }

    public void addSubscribedUser(User user) {
        this.subscribedUsers.add(user);
    }

    public void removeSubscribedUser(User user) {
        this.subscribedUsers.remove(user);
    }
}
