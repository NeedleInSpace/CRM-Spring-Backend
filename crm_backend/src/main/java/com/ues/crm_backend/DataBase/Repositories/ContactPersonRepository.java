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

@Service
public class ContactPersonRepository {

    @Autowired
    IContactPersonRepository contactPersonRepository;

    public ContactPersonRepository(IContactPersonRepository contactPersonRepository){
        this.contactPersonRepository = contactPersonRepository;
    }

    public List<ContactPerson> getAllContacts(){
        List<SerializedContactPerson> serializedContacts = contactPersonRepository.getAllContacts();

        if(serializedContacts == null) return null;

        List<ContactPerson> contacts = new ArrayList<>();
        for (SerializedContactPerson contact : serializedContacts) {
            contacts.add(new ContactPerson(contact));
        }

        return contacts;
    }

    public ContactPerson getContactPersonById(Long personId){
        SerializedContactPerson serializedContactPerson = contactPersonRepository.getContactPersonById(personId);

        if (serializedContactPerson == null) return null;

        return new ContactPerson(serializedContactPerson);
    }

    public List<ContactPerson> getAllContactPersonByCompanyId(Long companyId){
        List<SerializedContactPerson> serializedContactPersons = contactPersonRepository.getAllContactPersonByCompanyId(companyId);

        if (serializedContactPersons == null) return null;

        List<ContactPerson> contactPersons = new ArrayList<>();
        for (SerializedContactPerson contactPerson : serializedContactPersons) {
            contactPersons.add(new ContactPerson(contactPerson));
        }

        return contactPersons;
    }

    public void addNewContact(ContactPerson contactPerson){
        contactPersonRepository.save(new SerializedContactPerson(contactPerson));
    }

    @Transactional
    public void addNoteToContactPerson(Long personId, String note){
        SerializedContactPerson serializedContactPerson = contactPersonRepository.getContactPersonById(personId);

        if (serializedContactPerson == null) return;

        serializedContactPerson.addNewNote(note);

        contactPersonRepository.updateContactPersonNotes(personId, serializedContactPerson.getNotes());
    }

    public void deleteContactPerson(Long personId){
        SerializedContactPerson serializedContactPerson = contactPersonRepository.getContactPersonById(personId);

        if (serializedContactPerson == null) return;

        contactPersonRepository.delete(serializedContactPerson);
    }
}
