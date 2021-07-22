package com.codecool.demo.config;


import com.codecool.demo.mock_data.MockUserSupplier;
import com.codecool.demo.model.Event;
import com.codecool.demo.model.Task;
import com.codecool.demo.model.User;
import com.codecool.demo.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;

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

        // --- Create users --------------------------------
        User admin = new User(null, "Admin");
        User john = new User(null, "John");
        User jane = new User(null, "Jane");
        User ionel = new User(null, "Ionel");
        User giovanna = new User(null, "Giovanna");
        admin.addRole(UserRole.ADMIN);
        List<User> initialUsers = new LinkedList<>(List.of(admin, john, jane, ionel, giovanna));


        // --- Create task -------------------------------------------
        Task testTask = new Task(null,"Finish homework");
        admin.addTask(testTask); // when adding a task, its userOwner is also set within this method
        Task task1 = new Task(null, "Study");
        Task task2 = new Task(null, "Jog in the park");
        Task task3 = new Task(null, "Buy groceries");
        Task task4 = new Task(null, "Another task");
        Task task5 = new Task(null, "Yet Another Task (YAT)");
        Task task6 = new Task(null, "Evil mastermind scheme");
        Task task7 = new Task(null, "Get some sleep");

        List<Task> initialTasks = new ArrayList<>(List.of(testTask, task1, task2, task3, task4, task5, task6, task7));

        // --- Create events ---------------------------------------
        Event testEvent = new Event(null,"Starter Event","This is a test event...",42);
        Event event1 = new Event(
                null,
                "Start Spring Cleanup",
                "It's that time of the year. Time for renewal!",
                100
        );
        Event event2 = new Event(
                null,
                "Dreaded Study for Final Exam",
                "It's the final push for the mountain-top! Reach the peak of your academic studies!",
                2500
        );
        List<Event> initialEvents = new LinkedList<>(List.of(testEvent, event1, event2));


        // --- Give each user some tasks (Link Tasks with Users) ----------------
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

        // --- Have Users join some Events (Link Events with Users) ----------
        for (User user : initialUsers) {
            user.joinEvent(testEvent);
        }
        john.joinEvent(event1);
        jane.joinEvent(event2);
        ionel.joinEvent(event1);
        giovanna.joinEvent(event2);
        john.joinEvent(event2);


        // --- Save objects in Database ---
        try {
            for (User user : initialUsers) {
                user.setPassword(("123"));  // we would encode this with Security
                entityManager.persist(user);
            }
            for (Task task : initialTasks) {
                entityManager.persist(task);
            }
            for (Event event : initialEvents) {
                entityManager.persist(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
