package com.codecool.demo.repositories;

import com.codecool.demo.model.Task;
import org.springframework.data.repository.CrudRepository;


public interface TaskRepository extends CrudRepository<Task,Long> {
}
