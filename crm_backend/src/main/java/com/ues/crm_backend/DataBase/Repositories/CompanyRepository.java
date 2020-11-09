package com.ues.crm_backend.DataBase.Repositories;

import com.ues.crm_backend.DataBase.Interfaces.ICompanyRepository;
import com.ues.crm_backend.Models.Company.Company;
import com.ues.crm_backend.Models.Company.SerializedCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyRepository{

    @Autowired
    ICompanyRepository companyRepository;

    public CompanyRepository(ICompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public Company getCompanyById(Long companyId) {
        SerializedCompany serializedCompany = companyRepository.getCompanyById(companyId);

        if(serializedCompany == null) return null;

        return new Company(serializedCompany);
    }

    public List<Company> getAllCompanies() {
        List<SerializedCompany> serializedCompanies = companyRepository.getAllCompanies();

        if(serializedCompanies == null) return null;

        List<Company> companies = new ArrayList<>();
        for (SerializedCompany company : serializedCompanies) {
            companies.add(new Company(company));
        }

        return companies;
    }

    public List<Object[]> getAllCompaniesNameAndId() {
        List<Object[]> serializedRows = companyRepository.getAllCompaniesNameAndId();

        if(serializedRows == null) return null;

        /*List<Company> resultRows = new ArrayList<>();
        for (Object[] row : serializedRows) {
            companies.add(new Company(company));
        }*/

        return serializedRows;
    }

    public void addNewCompany(Company company){
        companyRepository.save(new SerializedCompany(company));
    }

    @Transactional
    public void addNoteToCompany(Long companyId, String note){
        SerializedCompany serializedCompany = companyRepository.getCompanyById(companyId);

        if (serializedCompany == null) return;

        serializedCompany.addNewNote(note);

        companyRepository.updateCompanyNotes(companyId, serializedCompany.getNotes());
    }

    @Transactional
    public void patchCompany(Long companyId, Company company){
        SerializedCompany serCompany = new SerializedCompany(company);

        companyRepository.patchCompany(companyId, serCompany.getName(), serCompany.getFullName(), serCompany.getKindOfActivity(),
                serCompany.getConsumptionVolume(), serCompany.getGeneratingCapacity(), serCompany.getInn(),
                serCompany.getKpp(), serCompany.getOkpo(), serCompany.getEmail(), serCompany.getPhone(),
                serCompany.getCreatorId(), serCompany.getChangerId(), serCompany.getNotes());
    }

    public void deleteCompany(Long companyId){
        SerializedCompany serializedCompany = companyRepository.getCompanyById(companyId);

        if(serializedCompany == null) return;

        companyRepository.delete(serializedCompany);
    }
}
