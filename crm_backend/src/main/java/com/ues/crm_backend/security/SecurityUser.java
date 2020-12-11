package com.ues.crm_backend.security;

import com.ues.crm_backend.Models.Employee;
import com.ues.crm_backend.Models.Role;
import com.ues.crm_backend.Models.Status;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;
    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser (Employee employee){
        return new org.springframework.security.core.userdetails.User(
                employee.getUsername(), employee.getPassword(),
                employee.getStatus().equals(Status.ACTIVE),
                employee.getStatus().equals(Status.ACTIVE),
                employee.getStatus().equals(Status.ACTIVE),
                employee.getStatus().equals(Status.ACTIVE),
                employee.getRole().getAuthorities()
        );
    }
}
