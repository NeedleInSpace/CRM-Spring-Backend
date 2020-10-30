package com.ues.crm_backend.Controllers;

import com.ues.crm_backend.DataBase.Repositories.ContactPesonRepository;
import com.ues.crm_backend.Models.Company;
import com.ues.crm_backend.Models.ContactPerson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactPersonController {

    private final ContactPesonRepository contactPesonRepository;

    public ContactPersonController(ContactPesonRepository contactPesonRepository){
        this.contactPesonRepository = contactPesonRepository;
    }

    @GetMapping(value = "/contacts/{id}")
    public ResponseEntity<ContactPerson> getContactPersonById(@PathVariable(name = "id") Long id){
        ContactPerson contactPerson = contactPesonRepository.getContactPersonById(id);

        return contactPerson != null
                ? new ResponseEntity<>(contactPerson, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/contacts/company/{id}")
    public ResponseEntity<List<ContactPerson>> getAllContactPersonByCompanyId(@PathVariable(name = "id") Long id){
        List<ContactPerson> contactPersons = contactPesonRepository.getAllContactPersonByCompanyId(id);

        return contactPersons != null && !contactPersons.isEmpty()
                ? new ResponseEntity<>(contactPersons, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/contacts")
    public ResponseEntity<?> addNewContact(@RequestBody ContactPerson contactPerson){
        contactPesonRepository.addNewContact(contactPerson);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(value = "/contacts/{id}")
    public ResponseEntity<?> patchContactPersonNotes(@PathVariable(name = "id") Long personId, @RequestParam String notes){
        contactPesonRepository.patchContactPersonNotes(personId, notes);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/contacts/{id}")
    public ResponseEntity<?> deleteContactPerson(@PathVariable(name = "id") Long id){
        contactPesonRepository.deleteContactPerson(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
