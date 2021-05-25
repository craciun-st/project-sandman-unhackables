package com.codecool.demo.controller;

import com.codecool.demo.dao.MockUserSupplier;

import com.codecool.demo.model.Task;
import com.codecool.demo.model.TaskAdder;
import com.codecool.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class TasksController {

    @Autowired
    MockUserSupplier mockUserSupplier;

    @Autowired
    EntityManager dbEntityManager;

//    UserRepo supplier;
//
//    @Autowired
//    public TasksController(UserRepo supplier) {
//        this.supplier = supplier;
//    }

    @GetMapping("/api/tasks") //   /tasks/?user=2422
    public List<Task> getAllTasks(

            @RequestParam(value = "user", defaultValue = "1")
            String valueForUser

    ) {
        Long parsedId = Long.parseLong(valueForUser);
//        Iterable<User> newUsers = supplier.findAll();
        Optional<User> maybeUser = mockUserSupplier.getAll().stream()
                .filter(user -> user.getId() == parsedId)
                .findFirst();
//        Optional<User> maybeUser = Optional.empty();
//        for (User user : newUsers) {
//            if (user.getId() == parsedId) {
//                maybeUser = Optional.of(user);
//            }
//        }

        return maybeUser.orElse(mockUserSupplier.getMockUser(parsedId))
                .getTasks();
    }

    @PostMapping("/api/task")
    public void addNewTaskForUser(
            @RequestBody
            TaskAdder jsonTaskAdder
    ) {
        int jsonId = jsonTaskAdder.getUserId();
        Task jsonTask = jsonTaskAdder.getTask();
        Optional<User> maybeUser = mockUserSupplier.getAll().stream()
                .filter(user -> user.getId() == jsonId)
                .findFirst();
//        Optional<User> maybeUser = Optional.empty();
//        for (User user : supplier.findAll()) {
//            if (user.getId() == jsonId) {
//                maybeUser = Optional.of(user);
//            }
//        }
        maybeUser.ifPresent(user -> user.addTask(jsonTask));
    }

    @PostMapping("/add-task")
    public void addNewTaskForUserFromForm(
            @RequestAttribute
            int userId,

            @RequestAttribute
            String taskName
    ) {

    }
}
