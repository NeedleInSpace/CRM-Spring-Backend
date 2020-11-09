package com.ues.crm_backend.DataBase.Interfaces;

import com.ues.crm_backend.Models.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "SELECT * FROM project WHERE project_id  = :projectId", nativeQuery = true)
    public Project findProjectById(@Param("projectId") Long projectId);
    @Modifying
    @Query(value = "UPDATE project SET project_short_name = :shortName, project_full_name = :fullName, stages_number = :stagesCount, member_number = :memberCount, project_description = :description WHERE project_id = :projectId", nativeQuery = true)
    public void updateProject(@Param("projectId") Long projectId, @Param("shortName") String shortName, @Param("fullName") String fullName, @Param("stagesCount") int stagesCount, @Param("memberCount") int memberCount, @Param("description") String description);
}
