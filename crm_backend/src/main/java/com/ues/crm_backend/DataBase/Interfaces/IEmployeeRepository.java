package com.ues.crm_backend.DataBase.Interfaces;

import com.ues.crm_backend.Models.Company.SerializedCompany;
import com.ues.crm_backend.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    /** Функция для поиска пользователя в БД*/
    Optional<Employee> findByUsername(String username);

    @Query(value = "SELECT employee_id FROM employee WHERE employee_name = :employeeName", nativeQuery = true)
    Long getEmployeeIdByName(@Param("employeeName") String employeeName);

    @Query(value = "SELECT employee.employee_id, employee.employee_name FROM employee join employee_role ON employee.employee_role_id = employee_role.role_id WHERE role_name = :employeeRole", nativeQuery = true)
    List<Object[]> findManagers(@Param("employeeRole") String employeeRole);

    @Query(value = "SELECT creator_id, count(*) " +
            "FROM contact_person " +
            "WHERE creation_date >= current_date - :period " +
            "GROUP BY creator_id", nativeQuery = true)
    List<Object[]> getContactCountInPeriod(@Param("period") int period);

    @Query(value = "SELECT creator_id, count(*) FROM company " +
            "WHERE creation_date >= current_date - :period " +
            "GROUP BY creator_id", nativeQuery = true)
    List<Object[]> getCompanyCountInPeriod(@Param("period") int period);
}
