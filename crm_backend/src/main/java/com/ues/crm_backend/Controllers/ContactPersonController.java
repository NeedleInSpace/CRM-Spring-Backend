package com.ues.crm_backend.Controllers;

import com.ues.crm_backend.DataBase.Repositories.ContactPersonRepository;
import com.ues.crm_backend.Models.ContactPerson.ContactPerson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactPersonController {

    private final ContactPersonRepository contactPersonRepository;

    public ContactPersonController(ContactPersonRepository contactPersonRepository){
        this.contactPersonRepository = contactPersonRepository;
    }

    @CrossOrigin
    @GetMapping(value = "api/contacts")
    public ResponseEntity<List<ContactPerson>> getAllContacts(){
        List<ContactPerson> contactPersons = contactPersonRepository.getAllContacts();

        return contactPersons != null && !contactPersons.isEmpty()
                ? new ResponseEntity<>(contactPersons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @GetMapping(value = "api/contacts/{id}")
    public ResponseEntity<ContactPerson> getContactPersonById(@PathVariable(name = "id") Long id){
        ContactPerson contactPerson = contactPersonRepository.getContactPersonById(id);

        return contactPerson != null
                ? new ResponseEntity<>(contactPerson, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @GetMapping(value = "api/contacts/company/{id}")
    public ResponseEntity<List<ContactPerson>> getAllContactPersonByCompanyId(@PathVariable(name = "id") Long id){
        List<ContactPerson> contactPersons = contactPersonRepository.getAllContactPersonByCompanyId(id);

        return contactPersons != null && !contactPersons.isEmpty()
                ? new ResponseEntity<>(contactPersons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin
    @PostMapping(value = "api/contacts")
    public ResponseEntity<?> addNewContact(@RequestBody ContactPerson contactPerson){
        contactPersonRepository.addNewContact(contactPerson);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping(value = "api/contacts/{id}")
    public ResponseEntity<?> addNoteToContactPerson(@PathVariable(name = "id") Long personId, @RequestParam String note){
        contactPersonRepository.addNoteToContactPerson(personId, note);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "api/contacts/{id}")
    public ResponseEntity<?> deleteContactPerson(@PathVariable(name = "id") Long id){
        contactPersonRepository.deleteContactPerson(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
