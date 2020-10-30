package com.ues.crm_backend.DataBase.Interfaces;

import com.ues.crm_backend.Models.Company.Company;
import com.ues.crm_backend.Models.Company.SerializedCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICompanyRepository extends JpaRepository<SerializedCompany, Long>{


    @Query(value = "SELECT * FROM company WHERE company_id = :companyId", nativeQuery = true)
    SerializedCompany getCompanyById(@Param("companyId") Long companyId);

    @Query(value = "SELECT * FROM company", nativeQuery = true)
    List<SerializedCompany> getAllCompanies();

    @Modifying
    @Query(value = "UPDATE company SET company_note = :notes " +
            "WHERE company_id  = :companyId", nativeQuery = true)
    void updateCompanyNotes(@Param("companyId") Long companyId, @Param("notes") String notes);

    @Modifying
    @Query(value = "UPDATE company SET company_name = :name, company_occupation = :kindOfActivity," +
            "consumption_volume_id = :consumptionVolume, generating_capacity = :generatingCapacity, inn = :INN, " +
            "kpp = :KPP, okpo = :OKPO, email = :email, phone = :phone, creator_id = :creatorId, " +
            "last_updater_id = :changerId, company_note  = :notes " +
            "WHERE company_id = :companyId", nativeQuery = true)
    void patchCompany(@Param("companyId") Long companyId, @Param("name") String name,
                               @Param("kindOfActivity") String kindOfActivity, @Param("consumptionVolume") Long consumptionVolume,
                               @Param("generatingCapacity") boolean generatingCapacity, @Param("INN") Long INN,
                               @Param("KPP")Long KPP, @Param("OKPO") Long OKPO, @Param("email")String email,
                               @Param("phone")Long phone, @Param("creatorId") Long creatorId,
                               @Param("changerId") Long changerId, @Param("notes") String notes);
}
