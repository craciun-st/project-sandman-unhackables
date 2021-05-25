package com.codecool.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.codecool.demo.model.User;
import com.codecool.demo.model.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

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

        User admin = new User(null, "Admin");
        Task testTask = new Task(null,"Finish homework");
        testTask.setUserOwner(admin);
        try {
            entityManager.persist(admin);
            entityManager.persist(testTask);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
