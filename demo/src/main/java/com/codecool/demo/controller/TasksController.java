package com.codecool.demo.controller;

import com.codecool.demo.dao.MockUserSupplier;

import com.codecool.demo.model.Task;
import com.codecool.demo.model.TaskAdder;
import com.codecool.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;

@RestController
public class TasksController {

    @Autowired
    MockUserSupplier mockUserSupplier;

    @Autowired
    EntityManager dbEntityManager;



    @GetMapping("/api/tasks") //   /tasks/?user=2422
    public List<Task> getAllTasks(

            @RequestParam(value = "user", defaultValue = "1")
            String valueForUser

    ) {
        Long parsedId = Long.parseLong(valueForUser);
        String queryString = "select obj from User as obj where obj.id = :parsedIdKey";
        TypedQuery<User> typedQuery = dbEntityManager.createQuery(queryString, User.class);

        typedQuery.setParameter("parsedIdKey", parsedId);
        Optional<User> maybeUser = typedQuery.getResultStream()
                .findFirst();
        List<Task> resultList;
        try {
            resultList = maybeUser.orElseThrow(NoSuchElementException::new).getTasks();
        } catch (NoSuchElementException e) {
            resultList = Collections.emptyList();
        }
        return resultList;
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
