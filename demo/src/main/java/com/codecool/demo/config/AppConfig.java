package com.codecool.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@EnableJpaRepositories("com.codecool.demo.repositories")
@EnableTransactionManagement
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
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    @Bean
    EntityManagerFactory entityManagerFactory() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default-db-manager");
        return emf;
    }
}
