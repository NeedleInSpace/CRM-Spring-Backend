package com.ues.crm_backend.DataBase.Interfaces;

import com.ues.crm_backend.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /** Функция для поиска пользователя в БД*/
    Optional<Employee> findByUsername(String username);
}
