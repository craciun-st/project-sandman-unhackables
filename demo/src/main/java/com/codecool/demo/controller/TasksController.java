package com.codecool.demo.controller;

import com.codecool.demo.controller.status.BadRequestHttpException;
import com.codecool.demo.controller.status.NotFoundHttpException;
import com.codecool.demo.model.Task;
import com.codecool.demo.model.TaskAdder;
import com.codecool.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class TasksController {


//    private MockUserSupplier mockUserSupplier;
    private EntityManager dbEntityManager;

    @Autowired
    public TasksController(EntityManager dbEntityManager) {
        this.dbEntityManager = dbEntityManager;
    }


    @GetMapping("/api/tasks") //   /tasks/?user=2422
    public List<Task> getAllTasks(

            @RequestParam(value = "user", defaultValue = "1")
            String valueForUser

    ) {
        long parsedId;
        try {
            parsedId = Long.parseLong(valueForUser);
        }
        catch (NumberFormatException e) {
            throw new BadRequestHttpException();
        }
        String queryString = "select obj from User as obj where obj.id = :parsedIdKey";
        TypedQuery<User> typedQuery = dbEntityManager.createQuery(queryString, User.class);

        typedQuery.setParameter("parsedIdKey", parsedId);
        Optional<List<Task>> maybeResultList = typedQuery.getResultStream()
                .findFirst()    // Optional<User>
                .map(User::getTasks);
        return maybeResultList.orElseThrow(NotFoundHttpException::new);
    }

    @PostMapping("/api/task")
    @Transactional
    public void addNewTaskForUser(
            @RequestBody
            TaskAdder jsonTaskAdder
    ) {
        long jsonId = jsonTaskAdder.getUserId();
        Task jsonTask = jsonTaskAdder.getTask();
        Task taskToWrite = new Task(null, jsonTask.getName());
        Optional<User> maybeUser = getAllUsers().stream()
                .filter(user -> user.getId() == jsonId)
                .findFirst();
        maybeUser.ifPresent(user -> {
            user.addTask(taskToWrite);
            dbEntityManager.persist(taskToWrite);
        });

    }

    @PostMapping("/add-task")
    public void addNewTaskForUserFromForm(
            @RequestAttribute
            int userId,

            @RequestAttribute
            String taskName
    ) {}


    private List<User> getAllUsers() {
        String queryString = "select u from User as u";
        TypedQuery<User> typedQuery = dbEntityManager.createQuery(queryString, User.class);

        return typedQuery.getResultList();
    }


}
