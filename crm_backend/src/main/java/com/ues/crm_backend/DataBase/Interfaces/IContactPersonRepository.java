package com.ues.crm_backend.DataBase.Interfaces;

import com.ues.crm_backend.Models.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IContactPersonRepository extends JpaRepository<ContactPerson, Long> {

    @Query(value = "SELECT * FROM contact_person WHERE contact_id  = :personId", nativeQuery = true)
    ContactPerson getContactPersonById(@Param("personId") Long personId);

    @Query(value = "SELECT * FROM contact_person WHERE contact_company_id  = :companyId", nativeQuery = true)
    List<ContactPerson> getAllContactPersonByCompanyId(@Param("companyId") Long companyId);

    @Modifying
    @Query(value = "UPDATE contact_person SET contact_note = :notes " +
            "WHERE contact_id  = :personId", nativeQuery = true)
    void patchContactPersonNotes(@Param("personId") Long companyId, @Param("notes") String notes);
}
