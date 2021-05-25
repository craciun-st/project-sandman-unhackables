package com.codecool.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan
public class AppConfig {

//    @Bean
//    EntityManager entityManager() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default-db-manager");
//        EntityManager theEntityManager = emf.createEntityManager();
//        return theEntityManager;
//    }

    @Bean
    EntityManagerFactory entityManagerFactory() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default-db-manager");
        return emf;
    }
}
