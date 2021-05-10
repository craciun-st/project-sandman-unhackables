package com.codecool.demo.dao;

import com.codecool.demo.model.Task;
import com.codecool.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class MockUserSupplier {
    private List<User> users = new ArrayList<>();

    public MockUserSupplier() {
        initMockUsers();
    }

    public User getMockUser(int id) {
        User someUser = new User(id, "User "+id);
        Random randomizer = new Random();
        for (int i=0; i<4; i++) {
            someUser.addTask(
                    getRandomTask(randomizer.nextInt())
            );
        }
        return someUser;
    }

    public void initMockUsers() {
        for (int i = 0; i < 10; i++) {
            users.add(getMockUser(i));
        }
    }

    public User getUser(int id) {
        if (id >= 0 && id < this.users.size()) {
            return users.get(id);
        }
        else {
            return getMockUser(id);
        }
    }

    public List<User> getAll() {

        return new ArrayList<>(users);
    }

    public Task getRandomTask(int index) {
        switch (mod(index, 5)) {
            case 0:
                return new Task(index, "Studying");
            case 1:
                return new Task(index, "Mock Tests");
            case 2:
                return new Task(index, "Assignments");
            case 3:
                return new Task(index, "Sports");
            case 4:
                return new Task(index, "General");
            default:
                return null;
        }
    }

    public static int mod(int a, int b) {
        return ((a % b) + b) % b;
    }
}
