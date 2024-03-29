package com.ues.crm_backend.Models;

import lombok.Data;

import javax.persistence.*;
/** Модель пользователя-сотрудника*/
@Data
@Entity
@Table(name="employee")
public class Employee {
    /** Идентификатор */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;

    /** Полное имя*/
    @Column(name="employee_name")
    private String name;

    /** Логин */
    @Column(name = "employee_login")
    private String username;

    /** Пароль*/
    @Column(name = "employee_hash_pas")
    private String password;

    /** Роль */
    @ManyToOne
    @JoinColumn(name = "employee_role_id")
    private  Role role;

    /** Текущий статус */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "employee_status")
    private Status status;

    public Long getId() {
        return employee_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
}
