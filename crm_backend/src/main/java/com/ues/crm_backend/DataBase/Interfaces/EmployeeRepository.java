package com.ues.crm_backend.DataBase.Interfaces;

import com.ues.crm_backend.Models.Company.SerializedCompany;
import com.ues.crm_backend.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /** Функция для поиска пользователя в БД*/
    Optional<Employee> findByUsername(String username);

    @Query(value = "SELECT employee_id FROM employee WHERE employee_name = :employeeName", nativeQuery = true)
    Long getEmployeeIdByName(@Param("employeeName") String employeeName);
}
