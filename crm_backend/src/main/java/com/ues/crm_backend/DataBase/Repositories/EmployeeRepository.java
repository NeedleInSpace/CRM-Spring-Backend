package com.ues.crm_backend.DataBase.Repositories;

import com.ues.crm_backend.DataBase.Interfaces.IEmployeeRepository;
import com.ues.crm_backend.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;

@Service
public class EmployeeRepository {
    @Autowired
    IEmployeeRepository iEmployeeRepository;

    public List<Object[]> getAllManagers() {
        return iEmployeeRepository.findManagers("MANAGER");
    }
}
