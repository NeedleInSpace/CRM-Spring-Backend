package com.ues.crm_backend.DataBase.Interfaces;

import com.ues.crm_backend.Models.Stage.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStageRepository extends JpaRepository<Stage, Long> {

    @Query(value = "SELECT * FROM stage WHERE project_id  = :projectId", nativeQuery = true)
    public List<Stage> findStagesByProjectId(@Param("projectId") Long projectId);

    @Query(value = "SELECT * FROM stage WHERE stage_id  = :stageId", nativeQuery = true)
    public Stage findStageById(@Param("stageId") Long stageId);
    @Modifying
    @Query(value = "UPDATE stage SET stage_name = :name, stage_result = :result, stage_description = :description, stage_number = :number WHERE stage_id = :stageId", nativeQuery = true)
    public void updateStage(@Param("stageId") int stageId, @Param("name") String name,@Param("result") String result,@Param("description") String description, @Param("number") int number);
}
