package com.ues.crm_backend.Controllers;

import com.ues.crm_backend.DataBase.Interfaces.EmployeeRepository;
import com.ues.crm_backend.Models.AuthenticationRequestDTD;
import com.ues.crm_backend.Models.Employee;
import com.ues.crm_backend.security.JWTTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private EmployeeRepository employeeRepository;
    private JWTTokenProvider jwtTokenProvider;

    public AuthRestController(AuthenticationManager authenticationManager, EmployeeRepository employeeRepository, JWTTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.employeeRepository = employeeRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTD request){
       try{
           UsernamePasswordAuthenticationToken uuu = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
           authenticationManager.authenticate(uuu);
           Employee employee = employeeRepository.findByUsername(request.getUsername()).orElseThrow(()-> new UsernameNotFoundException("User doesn't exists"));
           String token = jwtTokenProvider.createToken(request.getUsername(), employee.getRole().getRole());
           Map<Object, Object> response = new HashMap<>();
           response.put("username", request.getUsername());
           response.put("token", token);
           return ResponseEntity.ok(response);
       }catch (AuthenticationException e){
           return new ResponseEntity<>("Invalid username or password", HttpStatus.FORBIDDEN);
       }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
