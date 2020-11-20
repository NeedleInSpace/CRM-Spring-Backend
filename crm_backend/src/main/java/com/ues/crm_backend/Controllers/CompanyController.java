package com.ues.crm_backend.Controllers;

import com.ues.crm_backend.DataBase.Repositories.CompanyRepository;
import com.ues.crm_backend.Models.Company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс с REST контроллерами для компаний.
 *
 * Модель Company:
 * @see com.ues.crm_backend.Models.Company.Company;
 */
@RestController
public class CompanyController {

    /** Поле репозитория для взаимодействия с БД */
    private final CompanyRepository companyRepository;

    /** Конструктор класса */
    @Autowired
    public CompanyController(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    /**
     * Эндпоинт для получения списка компаний по названию.
     * @param name - название компании.
     * @return список компаний, удовлетворяющих условию.
     */
    @GetMapping(value = "api/search/companies")
    public ResponseEntity<List<Company>> getSuitable(@RequestParam String name){
        List<Company> companies = companyRepository.getAllCompanies();
        if(companies != null)
            companies = companies.stream().filter((company)->company.getFullName().contains(name)).collect(Collectors.toList());
        return new ResponseEntity<>(companies, HttpStatus.OK);

    }

    /**
     * Эндпоинт для получения списка всех компаний.
     * @return список всех компаний;
     */
    @GetMapping(value = "api/companies")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyRepository.getAllCompanies();

        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    /**
     * @deprecated Вроде бы нигде не используется, но это не точно.
     *
     * Эндпоинт для получения названия и id всех компаний.
     * @return company[0] - название компании,
     *         company[1] - id компании.
     */
    @GetMapping(value = "api/companyNamesWithId")
    public ResponseEntity<List<Object[]>> getAllCompaniesNameAndId() {
        List<Object[]> companies = companyRepository.getAllCompaniesNameAndId();

        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    /**
     * Эндпоинт для получения компании по id.
     * @param id - id интересующей компании.
     * @return искомая компания.
     */
    @GetMapping(value = "api/companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable(name = "id") Long id) {
        Company company = companyRepository.getCompanyById(id);

        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    /**
     * Эндпоинт для сохранения компании в БД.
     * @param company - сохраняемая компания.
     * @return HTTP статус выполненной операции.
     */
    @PostMapping(value = "api/companies")
    public ResponseEntity<?> addNewCompany(@RequestBody Company company){
        companyRepository.addNewCompany(company);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Эндпоинт для сохранения новой заметки о компании в БД.
     * @param companyId - id компании.
     * @param note - текст заметки.
     * @return HTTP статус выполненной операции.
     */
    @PostMapping(value = "api/companies/{id}")
    public ResponseEntity<?> addNoteToCompany(@PathVariable(name = "id") Long companyId, @RequestParam String note){
        companyRepository.addNoteToCompany(companyId, note);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Эндпоинт для изменения информации о компании в БД.
     * @param id - id компании.
     * @param company - новая информация о компании.
     * @return HTTP статус выполненной операции.
     */
    @PatchMapping(value = "api/companies/{id}")
    public ResponseEntity<?> patchCompany(@PathVariable(name = "id") Long id, @RequestBody Company company){
        companyRepository.patchCompany(id, company);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Эндпоинт для удаления компании из БД.
     * @param id - id удаляемой компании.
     * @return HTTP статус выполненной операции.
     */
    @DeleteMapping(value = "api/companies/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable(name = "id") Long id){
        companyRepository.deleteCompany(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
