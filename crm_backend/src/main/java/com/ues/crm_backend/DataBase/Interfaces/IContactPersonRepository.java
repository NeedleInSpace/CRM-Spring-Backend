package com.ues.crm_backend.DataBase.Interfaces;

import com.ues.crm_backend.Models.ContactPerson.SerializedContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IContactPersonRepository extends JpaRepository<SerializedContactPerson, Long> {

    @Query(value = "SELECT * FROM contact_person WHERE contact_id  = :personId", nativeQuery = true)
    SerializedContactPerson getContactPersonById(@Param("personId") Long personId);

    @Query(value = "SELECT * FROM contact_person WHERE contact_company_id  = :companyId", nativeQuery = true)
    List<SerializedContactPerson> getAllContactPersonByCompanyId(@Param("companyId") Long companyId);

    @Modifying
    @Query(value = "UPDATE contact_person SET contact_note = :notes " +
            "WHERE contact_id  = :personId", nativeQuery = true)
    void updateContactPersonNotes(@Param("personId") Long personId, @Param("notes") String notes);
}
