package com.codecool.demo.repositories;

import com.codecool.demo.model.Task;
import com.codecool.demo.model.User;
import org.springframework.data.repository.CrudRepository;


public interface TaskRepository extends CrudRepository<Task,Long> {
    Iterable<Task> findAllByUserOwner (User owner);
}
