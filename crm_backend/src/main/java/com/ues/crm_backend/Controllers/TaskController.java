package com.ues.crm_backend.Controllers;

import com.ues.crm_backend.DataBase.Repositories.TaskRepository;
import com.ues.crm_backend.Models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @PostMapping(value = "/tasks")
    public ResponseEntity<?> addTask(@RequestBody Task task){
        taskRepository.Insert(task);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/Tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskRepository.Select();

        return tasks != null &&  !tasks.isEmpty()
                ? new ResponseEntity<>(tasks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
