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

    public User getMockUser(Long id) {
        User someUser = new User(id, "User "+id);
        Random randomizer = new Random();
        for (int i=0; i<4; i++) {
            someUser.addTask(
                    getRandomTask(randomizer.nextLong())
            );
        }
        return someUser;
    }

    public void initMockUsers() {
        for (long i = 0; i < 10; i++) {
            users.add(getMockUser(i));
        }
    }

    public User getUser(long id) {
        if (id >= 0 && id < this.users.size()) {
            return users.get((int) id);
        }
        else {
            return getMockUser(id);
        }
    }

    public List<User> getAll() {

        return new ArrayList<>(users);
    }

    public Task getRandomTask(Long index) {
        if (mod(index, 5L) == 0L) {
            return new Task(index, "Studying");
        } else if (mod(index, 5L) == 1L) {
            return new Task(index, "Mock Tests");
        } else if (mod(index, 5L) == 2L) {
            return new Task(index, "Assignments");
        } else if (mod(index, 5L) == 3L) {
            return new Task(index, "Sports");
        } else if (mod(index, 5L) == 4L) {
            return new Task(index, "General");
        }
        return null;
    }

    public static Task getRandomTaskWithNullId(Long seed) {
        if (mod(seed, 5L) == 0L) {
            return new Task(null, "Studying");
        } else if (mod(seed, 5L) == 1L) {
            return new Task(null, "Mock Tests");
        } else if (mod(seed, 5L) == 2L) {
            return new Task(null, "Assignments");
        } else if (mod(seed, 5L) == 3L) {
            return new Task(null, "Sports");
        } else if (mod(seed, 5L) == 4L) {
            return new Task(null, "General");
        }
        return null;
    }

    public static Long mod(Long a, Long b) {
        return ((a % b) + b) % b;
    }
}
