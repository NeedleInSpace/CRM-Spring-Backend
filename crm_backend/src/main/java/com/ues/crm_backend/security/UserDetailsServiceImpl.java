package com.ues.crm_backend.security;

import com.ues.crm_backend.DataBase.Interfaces.IEmployeeRepository;
import com.ues.crm_backend.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IEmployeeRepository IEmployeeRepository;

    @Autowired
    public UserDetailsServiceImpl(IEmployeeRepository IEmployeeRepository) {
        this.IEmployeeRepository = IEmployeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = IEmployeeRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(employee);
    }
}
