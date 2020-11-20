package com.ues.crm_backend.DataBase.Interfaces;

import com.ues.crm_backend.Models.Company.Company;
import com.ues.crm_backend.Models.Company.SerializedCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Интерфейс для непосредственного взаимодейстаия с таблицей company в БД.
 * (Большинство стандартных операций уже реализовано в JpaRepository<Класс_конвертируемого_объекта, Тип_PK>)
 *
 * Модель Company:
 * @see com.ues.crm_backend.Models.Company.Company;
 */
public interface ICompanyRepository extends JpaRepository<SerializedCompany, Long>{

    /**
     * Запрос для получения компании по id.
     * @param companyId - id компании.
     * @return компания.
     */
    @Query(value = "SELECT * FROM company WHERE company_id = :companyId", nativeQuery = true)
    SerializedCompany getCompanyById(@Param("companyId") Long companyId);

    /**
     * Запрос для получения всех компаний.
     * @return Список всех компаний.
     */
    @Query(value = "SELECT * FROM company", nativeQuery = true)
    List<SerializedCompany> getAllCompanies();

    /**
     * @deprecated Вроде бы нигде не используется, но это не точно.
     *
     * Запрос для получения названия и id всех компаний.
     * @return company[0] - название компании,
     *         company[1] - id компании.
     */
    @Query(value = "SELECT company_name, company_id FROM company", nativeQuery = true)
    List<Object[]> getAllCompaniesNameAndId();

    /**
     * Запрос для обновления списка заметок у компании.
     * @param companyId - id компании.
     * @param notes - новый список заметок.
     */
    @Modifying
    @Query(value = "UPDATE company SET company_note = :notes " +
            "WHERE company_id  = :companyId", nativeQuery = true)
    void updateCompanyNotes(@Param("companyId") Long companyId, @Param("notes") String notes);

    /**
     * Запрос для обновления информации о компании.
     * (Если какое-то поле не изменилось то передавать нужно старое значение)
     *
     * @params Набор всех полей компании.
     */
    @Modifying
    @Query(value = "UPDATE company SET company_name = :name, company_full_name = :fullName," +
            "company_occupation = :kindOfActivity," +
            "consumption_volume_id = :consumptionVolume, generating_capacity = :generatingCapacity, inn = :INN, " +
            "kpp = :KPP, okpo = :OKPO, email = :email, phone = :phone, creator_id = :creatorId, " +
            "last_updater_id = :changerId, company_note  = :notes " +
            "WHERE company_id = :companyId", nativeQuery = true)
    void patchCompany(@Param("companyId") Long companyId, @Param("name") String name, @Param("fullName") String fullName,
                               @Param("kindOfActivity") String kindOfActivity, @Param("consumptionVolume") Long consumptionVolume,
                               @Param("generatingCapacity") boolean generatingCapacity, @Param("INN") Long INN,
                               @Param("KPP")Long KPP, @Param("OKPO") Long OKPO, @Param("email")String email,
                               @Param("phone")Long phone, @Param("creatorId") Long creatorId,
                               @Param("changerId") Long changerId, @Param("notes") String notes);
}
