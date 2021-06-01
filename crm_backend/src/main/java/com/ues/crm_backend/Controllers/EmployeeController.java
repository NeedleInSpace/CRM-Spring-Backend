package com.ues.crm_backend.Controllers;

import com.ues.crm_backend.DataBase.Repositories.EmployeeRepository;
import com.ues.crm_backend.Models.Employee;
import com.ues.crm_backend.Models.Project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Tuple;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value="/managers")
    public ResponseEntity<?> getManagers() {
        try {
            List<Object[]> managers = employeeRepository.getAllManagers();
            return new ResponseEntity<>(managers, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Not found", HttpStatus.OK);
        }
    }
}
