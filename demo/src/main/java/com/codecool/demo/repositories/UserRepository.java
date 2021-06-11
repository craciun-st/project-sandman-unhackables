package com.codecool.demo.repositories;

import com.codecool.demo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findUserByName(String name);
}
