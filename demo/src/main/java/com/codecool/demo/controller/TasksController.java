package com.codecool.demo.controller;
//
//import com.codecool.demo.controller.status.BadRequestHttpException;
//import com.codecool.demo.controller.status.NotFoundHttpException;
//import com.codecool.demo.model.Task;
//import com.codecool.demo.model.User;
//import com.codecool.demo.repositories.TaskRepository;
//import com.codecool.demo.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;

//@RestController
//public class TasksController {
//
//
////    private MockUserSupplier mockUserSupplier;
////    private EntityManager dbEntityManager;
//    private final UserRepository userRepo;
//    private final TaskRepository taskRepo;
//
////    @Autowired
////    public TasksController(EntityManager dbEntityManager) {
////        this.dbEntityManager = dbEntityManager;
////    }
//
//    @Autowired
//    public TasksController(UserRepository userRepo, TaskRepository taskRepo) {
//        this.userRepo = userRepo;
//        this.taskRepo = taskRepo;
//    }
//
//
//    @GetMapping("/api/tasks") //   /tasks/?user=2422
//    public List<Task> getAllTasks(
//
//            @RequestParam(value = "user", defaultValue = "1")
//            String valueForUser
//
//    ) {
//        long parsedId;
//        try {
//            parsedId = Long.parseLong(valueForUser);
//        }
//        catch (NumberFormatException e) {
//            throw new BadRequestHttpException();
//        }
//        String queryString = "select obj from User as obj where obj.id = :parsedIdKey";
////        TypedQuery<User> typedQuery = dbEntityManager.createQuery(queryString, User.class);
////        typedQuery.setParameter("parsedIdKey", parsedId);
//
//        Optional<User> maybeUser = userRepo.findById(parsedId);
////        Optional<List<Task>> maybeResultList = typedQuery.getResultStream()
////                .findFirst()    // Optional<User>
////                .map(User::getTasks);
//        Optional<List<Task>> maybeResultList = maybeUser.map(User::getTasks);
//        return maybeResultList.orElseThrow(NotFoundHttpException::new);
//    }
//
//    @PostMapping("/api/task")
//    @Transactional
//    public void appendNewTasksForUser(
//            @RequestBody
//            List<Task> jsonTaskList
//    ) {
//        long userId = 3L;   // gotten from session (when implemented)
////        Optional<User> maybeUser = getAllUsers().stream()
////                .filter(user -> user.getId() == userId)
////                .findFirst();
//        Optional<User> maybeUser = userRepo.findById(userId);
//        for (Task jsonTask : jsonTaskList) {
//            //TODO validate jsonTask with a validator
//
//            maybeUser.ifPresent(user -> {
//                user.addTask(jsonTask);
////                dbEntityManager.persist(jsonTask);
//                taskRepo.save(jsonTask);
//            });
//
//
//        }
//    }
//
//    @PostMapping("/add-task")
//    public void addNewTaskForUserFromForm(
//            @RequestAttribute
//            int userId,
//
//            @RequestAttribute
//            String taskName
//    ) {}
//
//
//    private List<User> getAllUsers() {
////        String queryString = "select u from User as u";
////        TypedQuery<User> typedQuery = dbEntityManager.createQuery(queryString, User.class);
////        return typedQuery.getResultList();
//        List<User> resultList = new ArrayList<>();
//        for (User user : userRepo.findAll()) {
//            resultList.add(user);
//        }
//
//        return resultList;
//    }

//
//}


import com.codecool.demo.repositories.TaskRepository;
import com.codecool.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TasksController {


    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TasksController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


}