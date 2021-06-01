package com.ues.crm_backend.Controllers;

import com.ues.crm_backend.DataBase.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyticsController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("api/analytics/task/count")
    public ResponseEntity<?> getAssignmentCount() {
        return new ResponseEntity<>(taskRepository.getTasksCountStatus(), HttpStatus.OK);
    }
}
