package com.codecool.demo;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UserSupplier {
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
