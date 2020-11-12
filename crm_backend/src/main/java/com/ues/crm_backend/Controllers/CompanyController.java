package com.ues.crm_backend.Controllers;

import com.ues.crm_backend.DataBase.Repositories.CompanyRepository;
import com.ues.crm_backend.Models.Company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    @GetMapping(value = "api/companies")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyRepository.getAllCompanies();

        return companies != null && !companies.isEmpty()
                ? new ResponseEntity<>(companies, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "api/companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable(name = "id") Long id) {
        Company company = companyRepository.getCompanyById(id);

        return company != null
                ? new ResponseEntity<>(company, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "api/companies")
    public ResponseEntity<?> addNewCompany(@RequestBody Company company){
        companyRepository.addNewCompany(company);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "api/companies/{id}")
    public ResponseEntity<?> addNoteToCompany(@PathVariable(name = "id") Long companyId, @RequestParam String note){
        companyRepository.addNoteToCompany(companyId, note);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(value = "api/companies/{id}")
    public ResponseEntity<?> patchCompany(@PathVariable(name = "id") Long id, @RequestBody Company company){
        companyRepository.patchCompany(id, company);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "api/companies/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable(name = "id") Long id){
        companyRepository.deleteCompany(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}