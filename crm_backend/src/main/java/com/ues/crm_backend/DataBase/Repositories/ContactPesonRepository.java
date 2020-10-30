package com.ues.crm_backend.DataBase.Repositories;

import com.ues.crm_backend.DataBase.Interfaces.IContactPersonRepository;
import com.ues.crm_backend.Models.ContactPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactPesonRepository {

    @Autowired
    IContactPersonRepository contactPersonRepository;

    public ContactPesonRepository(IContactPersonRepository contactPersonRepository){
        this.contactPersonRepository = contactPersonRepository;
    }

    public ContactPerson getContactPersonById(Long personId){
        return contactPersonRepository.getContactPersonById(personId);
    }

    public List<ContactPerson> getAllContactPersonByCompanyId(Long companyId){
        return contactPersonRepository.getAllContactPersonByCompanyId(companyId);
    }

    public void addNewContact(ContactPerson contactPerson){
        contactPersonRepository.save(contactPerson);
    }

    @Transactional
    public void patchContactPersonNotes(Long personId, String notes){
        contactPersonRepository.patchContactPersonNotes(personId, notes);
    }

    public void deleteContactPerson(Long personId){
        ContactPerson contactPerson = contactPersonRepository.getContactPersonById(personId);

        contactPersonRepository.delete(contactPerson);
    }
}
