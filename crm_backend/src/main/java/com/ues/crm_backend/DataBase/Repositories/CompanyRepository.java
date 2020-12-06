package com.ues.crm_backend.DataBase.Repositories;

import com.ues.crm_backend.DataBase.Interfaces.ICompanyRepository;
import com.ues.crm_backend.Models.Company.Company;
import com.ues.crm_backend.Models.Company.SerializedCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс репозитория компаний для взаимодействия с БД.
 *
 * Класс контроллера:
 * @see com.ues.crm_backend.Controllers.CompanyController;
 *
 * Модель Company:
 * @see com.ues.crm_backend.Models.Company.Company;
 */
@Service
public class CompanyRepository{

    /** Интерфейс репозитория */
    @Autowired
    ICompanyRepository companyRepository;

    /** Конструктор класса */
    public CompanyRepository(ICompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    /**
     * Обработчик эндпоинта getCompanyById.
     * @param companyId - id интересующей компании.
     * @return искомая компания.
     */
    public Company getCompanyById(Long companyId) {
        SerializedCompany serializedCompany = companyRepository.getCompanyById(companyId);

        if(serializedCompany == null) return null;

        return new Company(serializedCompany);
    }

    /**
     * Обработчик эндпоинта getAllCompanies.
     * @return список всех компаний;
     */
    public List<Company> getAllCompanies() {
        List<SerializedCompany> serializedCompanies = companyRepository.getAllCompanies();

        if(serializedCompanies == null) return null;

        List<Company> companies = new ArrayList<>();
        for (SerializedCompany company : serializedCompanies) {
            companies.add(new Company(company));
        }

        return companies;
    }

    /**
     * @deprecated Вроде бы нигде не используется, но это не точно.
     *
     * Обработчик эндпоинта getAllCompaniesNameAndId.
     * @return company[0] - название компании,
     *         company[1] - id компании.
     */
    public List<Object[]> getAllCompaniesNameAndId() {
        List<Object[]> serializedRows = companyRepository.getAllCompaniesNameAndId();

        if(serializedRows == null) return null;

        /*List<Company> resultRows = new ArrayList<>();
        for (Object[] row : serializedRows) {
            companies.add(new Company(company));
        }*/

        return serializedRows;
    }

    /**
     * Обработчик эндпоинта addNewCompany.
     * @param company - сохраняемая компания.
     */
    public void addNewCompany(Company company){
        companyRepository.save(new SerializedCompany(company));
    }

    /**
     * Обработчик эндпоинта addNoteToCompany.
     * @param companyId - id компании.
     * @param note - текст заметки.
     */
    @Transactional
    public void addNoteToCompany(Long companyId, String note){
        SerializedCompany serializedCompany = companyRepository.getCompanyById(companyId);

        if (serializedCompany == null) return;

        serializedCompany.addNewNote(note);

        companyRepository.updateCompanyNotes(companyId, serializedCompany.getNotes());
    }

    /**
     * Обработчик эндпоинта patchCompany.
     * @param companyId - id компании.
     * @param company - новая информация о компании.
     */
    @Transactional
    public void patchCompany(Long companyId, Company company){
        SerializedCompany serCompany = new SerializedCompany(company);

        companyRepository.patchCompany(companyId, serCompany.getName(), serCompany.getFullName(), serCompany.getKindOfActivity(),
                serCompany.getConsumptionVolume(), serCompany.getGeneratingCapacity(), serCompany.getInn(),
                serCompany.getKpp(), serCompany.getOkpo(), serCompany.getEmail(), serCompany.getPhone(),
                serCompany.getCreatorId(), serCompany.getChangerId(), serCompany.getNotes());
    }

    /**
     * Обработчик эндпоинта deleteCompany.
     * @param companyId - id удаляемой компании.
     */
    public void deleteCompany(Long companyId){
        SerializedCompany serializedCompany = companyRepository.getCompanyById(companyId);

        if(serializedCompany == null) return;

        companyRepository.delete(serializedCompany);
    }
}
