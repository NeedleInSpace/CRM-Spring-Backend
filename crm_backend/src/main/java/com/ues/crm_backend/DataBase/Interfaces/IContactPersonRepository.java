package com.ues.crm_backend.DataBase.Interfaces;

import com.ues.crm_backend.Models.ContactPerson.SerializedContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Интерфейс для непосредственного взаимодейстаия с таблицей contact_person в БД.
 * (Большинство стандартных операций уже реализовано в JpaRepository<Класс_конвертируемого_объекта, Тип_PK>)
 *
 * Модель ContactPerson:
 * @see com.ues.crm_backend.Models.ContactPerson.ContactPerson;
 */
public interface IContactPersonRepository extends JpaRepository<SerializedContactPerson, Long> {

    /**
     * Запрос для получения контактного лица по id.
     * @param personId - id контактного лица.
     * @return контактное лицо.
     */
    @Query(value = "SELECT * FROM contact_person WHERE contact_id  = :personId", nativeQuery = true)
    SerializedContactPerson getContactPersonById(@Param("personId") Long personId);

    /**
     * Запрос для получения всех контактных лиц.
     * @return Список всех контактных лиц.
     */
    @Query(value = "SELECT * FROM contact_person", nativeQuery = true)
    List<SerializedContactPerson> getAllContacts();

    /**
     * Запрос для получения всех контактных лиц из компании.
     * @param companyId - id компании.
     * @return список контактных лиц.
     */
    @Query(value = "SELECT * FROM contact_person WHERE contact_company_id  = :companyId", nativeQuery = true)
    List<SerializedContactPerson> getAllContactPersonByCompanyId(@Param("companyId") Long companyId);


    /**
     * Запрос для обновления списка заметок у контактного лица.
     * @param personId - id контактного лица.
     * @param notes - новый список заметок.
     */
    @Modifying
    @Query(value = "UPDATE contact_person SET contact_note = :notes " +
            "WHERE contact_id  = :personId", nativeQuery = true)
    void updateContactPersonNotes(@Param("personId") Long personId, @Param("notes") String notes);

    @Modifying
    @Query(value = "UPDATE contact_person SET contact_name = :contactName, contact_company_id = :companyId," +
            "contact_position = :position, make_decision = :makeDecision, main_email = :mainEmail, " +
            "other_emails = :otherEmails, contact_note = :notes, creator_id = :creatorId, " +
            "last_updater_id = :lastUpdaterId, main_phone = :mainPhone, other_phones = :otherPhones " +
            "WHERE contact_id  = :contactPersonId", nativeQuery = true)
    void patchContactPerson(@Param("contactPersonId") Long contactPersonId, @Param("contactName") String contactName, @Param("companyId") Long companyId,
                      @Param("position") String position, @Param("makeDecision") boolean makeDecision,
                      @Param("mainEmail") String mainEmail, @Param("otherEmails") String otherEmails,
                      @Param("notes")String notes, @Param("creatorId") Long creatorId, @Param("lastUpdaterId")Long lastUpdaterId,
                      @Param("mainPhone")Long mainPhone, @Param("otherPhones") String otherPhones);
}
