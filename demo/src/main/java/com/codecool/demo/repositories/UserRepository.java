package com.codecool.demo.repositories;

import com.codecool.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {
}
