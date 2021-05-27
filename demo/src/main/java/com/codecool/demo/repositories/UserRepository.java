package com.codecool.demo.repositories;

import com.codecool.demo.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Long> {
}
