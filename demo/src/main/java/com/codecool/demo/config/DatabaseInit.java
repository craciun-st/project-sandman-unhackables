package com.codecool.demo.config;


import com.codecool.demo.mock_data.MockUserSupplier;
import com.codecool.demo.model.Task;
import com.codecool.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseInit implements ApplicationRunner {
    
    private EntityManager entityManager;


    @Autowired
    public DatabaseInit(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        Random randomizer = new Random();
        User admin = new User(null, "Admin");
        User john = new User(null, "John");
        User jane = new User(null, "Jane");
        User ionel = new User(null, "Ionel");
        User giovanna = new User(null, "Giovanna");
        List<User> initialUsers = new LinkedList<>();
        initialUsers.add(admin);
        initialUsers.add(john);
        initialUsers.add(jane);
        initialUsers.add(ionel);
        initialUsers.add(giovanna);


        Task testTask = new Task(null,"Finish homework");
        admin.addTask(testTask); // when adding a task, its userOwner is also set within this method
        Task task1 = new Task(null, "Study");
        Task task2 = new Task(null, "Jog in the park");
        Task task3 = new Task(null, "Buy groceries");
        Task task4 = new Task(null, "Another task");
        Task task5 = new Task(null, "Yet Another Task (YAT)");
        Task task6 = new Task(null, "Evil mastermind scheme");
        Task task7 = new Task(null, "Get some sleep");

        List<Task> initialTasks = new ArrayList<>();
        initialTasks.add(testTask);
        initialTasks.add(task1);
        initialTasks.add(task2);
        initialTasks.add(task3);
        initialTasks.add(task4);
        initialTasks.add(task5);
        initialTasks.add(task6);
        initialTasks.add(task7);
        for (int j = 0; j < 7; j++) {
            initialTasks.add(
                    MockUserSupplier.getRandomTaskWithNullId(randomizer.nextLong()));
        }

        for (int i = 0; i < 4; i++) {

            john.addTask(initialTasks.get(i+1));

        }
        for (int i = 0; i<3; i++) {

            jane.addTask(initialTasks.get(i+5));

        }

        for (int i = 0; i < 4; i++) {
            ionel.addTask(initialTasks.get(i+8));
        }

        for (int i = 0; i < 3; i++) {
            giovanna.addTask(initialTasks.get(i+12));
        }

        try {
            for (User user : initialUsers) {
                user.setPassword(passwordEncoder.encode("123"));
                entityManager.persist(user);
            }
            for (Task task : initialTasks) {
                entityManager.persist(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
