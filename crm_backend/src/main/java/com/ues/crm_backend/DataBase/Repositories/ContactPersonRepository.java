package com.ues.crm_backend.DataBase.Repositories;

import com.ues.crm_backend.DataBase.Interfaces.IContactPersonRepository;
import com.ues.crm_backend.Models.Company.Company;
import com.ues.crm_backend.Models.Company.SerializedCompany;
import com.ues.crm_backend.Models.ContactPerson.ContactPerson;
import com.ues.crm_backend.Models.ContactPerson.SerializedContactPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс репозитория компаний для взаимодействия с БД.
 *
 * Класс контроллера:
 * @see com.ues.crm_backend.Controllers.ContactPersonController;
 *
 * Модель Company:
 * @see com.ues.crm_backend.Models.ContactPerson.ContactPerson;
 */
@Service
public class ContactPersonRepository {

    /** Интерфейс репозитория */
    @Autowired
    IContactPersonRepository contactPersonRepository;

    /** Конструктор класса */
    public ContactPersonRepository(IContactPersonRepository contactPersonRepository){
        this.contactPersonRepository = contactPersonRepository;
    }

    /**
     * Обработчик эндпоинта getAllContacts.
     * @return список всех контактных лиц.
     */
    public List<ContactPerson> getAllContacts(){
        List<SerializedContactPerson> serializedContacts = contactPersonRepository.getAllContacts();

        if(serializedContacts == null) return null;

        List<ContactPerson> contacts = new ArrayList<>();
        for (SerializedContactPerson contact : serializedContacts) {
            contacts.add(new ContactPerson(contact));
        }

        return contacts;
    }

    /**
     * Обработчик эндпоинта getContactPersonById.
     * @param personId - id интересующего контактного лица.
     * @return искомое контактное лицо.
     */
    public ContactPerson getContactPersonById(Long personId){
        SerializedContactPerson serializedContactPerson = contactPersonRepository.getContactPersonById(personId);

        if (serializedContactPerson == null) return null;

        return new ContactPerson(serializedContactPerson);
    }

    /**
     * Обработчик эндпоинта getAllContactPersonByCompanyId.
     * @param companyId - id компании.
     * @return список контактных лиц.
     */
    public List<ContactPerson> getAllContactPersonByCompanyId(Long companyId){
        List<SerializedContactPerson> serializedContactPersons = contactPersonRepository.getAllContactPersonByCompanyId(companyId);

        if (serializedContactPersons == null) return null;

        List<ContactPerson> contactPersons = new ArrayList<>();
        for (SerializedContactPerson contactPerson : serializedContactPersons) {
            contactPersons.add(new ContactPerson(contactPerson));
        }

        return contactPersons;
    }

    /**
     * Обработчик эндпоинта addNewContact.
     * @param contactPerson - сохраняемое контактное лицо.
     */
    public void addNewContact(ContactPerson contactPerson){
        contactPersonRepository.save(new SerializedContactPerson(contactPerson));
    }

    /**
     * Обработчик эндпоинта addNoteToContactPerson.
     * @param personId - id контактного лица.
     * @param note - текст заметки.
     */
    @Transactional
    public void addNoteToContactPerson(Long personId, String note){
        SerializedContactPerson serializedContactPerson = contactPersonRepository.getContactPersonById(personId);

        if (serializedContactPerson == null) return;

        serializedContactPerson.addNewNote(note);

        contactPersonRepository.updateContactPersonNotes(personId, serializedContactPerson.getNotes());
    }

    /**
     * Обработчик эндпоинта deleteContactPerson.
     * @param personId - id удаляемого контактного лица.
     */
    public void deleteContactPerson(Long personId){
        SerializedContactPerson serializedContactPerson = contactPersonRepository.getContactPersonById(personId);

        if (serializedContactPerson == null) return;

        contactPersonRepository.delete(serializedContactPerson);
    }

    @Transactional
    public void patchContactPerson(Long contactId, ContactPerson contact){
        SerializedContactPerson serContact = new SerializedContactPerson(contact);

        contactPersonRepository.patchContactPerson(contactId, serContact.getContactName(), serContact.getCompanyId(),
                serContact.getPosition(), serContact.getMakeDecision(), serContact.getMainEmail(),
                serContact.getOtherEmails(), serContact.getNotes(), serContact.getCreatorId(),
                serContact.getLastUpdaterId(), serContact.getMainPhone(), serContact.getOtherPhones());
    }
}
