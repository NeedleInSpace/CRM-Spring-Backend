package com.ues.crm_backend.DataBase.Repositories;

import com.ues.crm_backend.DataBase.Interfaces.ICompanyRepository;
import com.ues.crm_backend.Models.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyRepository{

    @Autowired
    ICompanyRepository companyRepository;

    public CompanyRepository(ICompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public Company getCompanyById(Long companyId) {
        return companyRepository.getCompanyById(companyId);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    public void addNewCompany(Company company){
        companyRepository.save(company);
    }

    @Transactional
    public void patchCompany(Long companyId, Company company){
        companyRepository.patchCompany(companyId, company.getName(), company.getKindOfActivity(),
                company.getConsumptionVolume(), company.getGeneratingCapacity(), company.getINN(),
                company.getKPP(), company.getOKPO(), company.getEmail(), company.getPhone(),
                company.getCreatorId(), company.getChangerId(), company.getNotes());
    }

    public void deleteCompany(Long companyId){
        Company company = this.getCompanyById(companyId);

        companyRepository.delete(company);
    }
}
