package com.codecool.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TasksController {

    @Autowired
    UserSupplier supplier;

    @GetMapping("/tasks") //   /tasks/?user=2422
    public List<Task> getAllTasks(

            @RequestParam(value = "user", defaultValue = "1")
            String valueForUser

    ) {
        int parsedId = Integer.parseInt(valueForUser);
        return supplier.getMockUser(parsedId).getTasks();
    }
}
