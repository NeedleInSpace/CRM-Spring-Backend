package com.ues.crm_backend.Controllers;

import com.ues.crm_backend.DataBase.Repositories.EmployeeRepository;
import com.ues.crm_backend.DataBase.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AnalyticsController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("api/analytics/task/count")
    public ResponseEntity<?> getAssignmentCount() {
        return new ResponseEntity<>(taskRepository.getTasksCountStatus(), HttpStatus.OK);
    }

    @GetMapping("api/analytics/task/count/project/{projectId}")
    public ResponseEntity<?> getAssignmentCountByProject(@PathVariable("projectId") Long projectId) {
        return new ResponseEntity<>(taskRepository.getTasksCountByProject(projectId), HttpStatus.OK);
    }

    @GetMapping("api/analytics/task/count/employee/{employeeId}")
    public ResponseEntity<?> getAssignmentCountByEmployee(@PathVariable("employeeId") Long employeeId) {
        return new ResponseEntity<>(taskRepository.getTasksCountByEmployee(employeeId), HttpStatus.OK);
    }

    @GetMapping(value = "api/analytics/employee/contact")
    public ResponseEntity<?> getNewEmployeeContactCount() {
        List<Map<Object, Object>> data = new ArrayList<Map<Object, Object>>();
        data.add(employeeRepository.getNewContactCount());
        data.add(employeeRepository.getNewCompanyCount());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
