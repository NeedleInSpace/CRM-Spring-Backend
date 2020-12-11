package com.ues.crm_backend.Models;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
/** Модель роли пользователя*/
@Entity
@Table(name = "employee_role")
public class Role {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int roleId ;
    @Column(name = "role_name")
    private String role;


    public int getId() {
        return roleId;
    }

    public String getRole() {
        return role;
    }

    /** Функция получения привилегий */
    public  Set<SimpleGrantedAuthority> getAuthorities(){
        SimpleGrantedAuthority sga = new SimpleGrantedAuthority(role);
        Set<SimpleGrantedAuthority> set = new HashSet<>();
        set.add(sga);
        return set;
    }
}
