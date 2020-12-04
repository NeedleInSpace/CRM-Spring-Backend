package com.ues.crm_backend.Controllers;

import com.ues.crm_backend.DataBase.Repositories.ContactPersonRepository;
import com.ues.crm_backend.Models.Company.Company;
import com.ues.crm_backend.Models.ContactPerson.ContactPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс с REST контроллерами для контактных лиц.
 *
 * Модель ContactPerson:
 * @see com.ues.crm_backend.Models.ContactPerson.ContactPerson;
 */
@RestController
public class ContactPersonController {

    /** Поле репозитория для взаимодействия с БД */
    private final ContactPersonRepository contactPersonRepository;

    /** Конструктор класса */
    @Autowired
    public ContactPersonController(ContactPersonRepository contactPersonRepository){
        this.contactPersonRepository = contactPersonRepository;
    }

    /**
     * Эндпоинт для получения списка всех контактных лиц.
     * @return список всех контактных лиц.
     */
    @GetMapping(value = "api/contacts")
    public ResponseEntity<List<ContactPerson>> getAllContacts(){
        List<ContactPerson> contactPersons = contactPersonRepository.getAllContacts();

        return new ResponseEntity<>(contactPersons, HttpStatus.OK);
    }

    /**
     * Эндпоинт для получения контактного лица по id.
     * @param id - id интересующего контактного лица.
     * @return искомое контактное лицо.
     */
    @GetMapping(value = "api/contacts/{id}")
    public ResponseEntity<ContactPerson> getContactPersonById(@PathVariable(name = "id") Long id){
        ContactPerson contactPerson = contactPersonRepository.getContactPersonById(id);

        return new ResponseEntity<>(contactPerson, HttpStatus.OK);
    }

    /**
     * Эндпоинт для получения всех контактных лиц из компании.
     * @param id - id компании.
     * @return список контактных лиц.
     */
    @GetMapping(value = "api/contacts/company/{id}")
    public ResponseEntity<List<ContactPerson>> getAllContactPersonByCompanyId(@PathVariable(name = "id") Long id){
        List<ContactPerson> contactPersons = contactPersonRepository.getAllContactPersonByCompanyId(id);

        return new ResponseEntity<>(contactPersons, HttpStatus.OK);
    }

    /**
     * Эндпоинт для получения контактных лиц по их имени.
     * @param name - имя контактного лица.
     * @return список контактных лиц, удовлетворяющих условию.
     */
    @GetMapping(value = "api/search/contacts")
    public ResponseEntity<List<ContactPerson>> getSuitable(@RequestParam String name){
        List<ContactPerson> contactPersons = contactPersonRepository.getAllContacts();
        contactPersons = contactPersons.stream()
                .filter((contactPerson)->contactPerson.getContactName().contains(name))
                .collect(Collectors.toList());
        return new ResponseEntity<>(contactPersons, HttpStatus.OK);
    }

    /**
     * Эндпоинт для сохранения контактного лица в БД.
     * @param contactPerson - сохраняемое контактное лицо.
     * @return HTTP статус выполненной операции.
     */
    @PostMapping(value = "api/contacts")
    public ResponseEntity<?> addNewContact(@RequestBody ContactPerson contactPerson){
        contactPersonRepository.addNewContact(contactPerson);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Эндпоинт для сохранения новой заметки о контактном лице в БД.
     * @param personId - id контактного лица.
     * @param note - текст заметки.
     * @return HTTP статус выполненной операции.
     */
    @PostMapping(value = "api/contacts/{id}")
    public ResponseEntity<?> addNoteToContactPerson(@PathVariable(name = "id") Long personId, @RequestParam String note){
        contactPersonRepository.addNoteToContactPerson(personId, note);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Эндпоинт для удаления контактного лица из БД.
     * @param id - id удаляемого контактного лица.
     * @return HTTP статус выполненной операции.
     */
    @DeleteMapping(value = "api/contacts/{id}")
    public ResponseEntity<?> deleteContactPerson(@PathVariable(name = "id") Long id){
        contactPersonRepository.deleteContactPerson(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "api/contacts/{id}")
    public ResponseEntity<?> patchCompany(@PathVariable(name = "id") Long id, @RequestBody ContactPerson contact){
        contactPersonRepository.patchContactPerson(id, contact);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
