package com.ues.crm_backend.DataBase.Interfaces;

import com.ues.crm_backend.Models.ContactPerson.SerializedContactPerson;
import com.ues.crm_backend.Models.Task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

public interface ITaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT * FROM task WHERE employee_id  = :employeeId", nativeQuery = true)
    List<Task> getAllEmployeeTasks(@Param("employeeId") Long employeeId);

    @Modifying
    @Query(value = "UPDATE task SET task_name = :taskName, task_project_id = :taskProjectId," +
            " task_stage_id = :taskStageId, task_company_id = :taskCompanyId, " +
            "task_date = :taskDate, task_time = :taskTime, " +
            "task_place = :taskPlace, task_result = :taskResult, task_description = :taskDescription, " +
            "task_status_id = :taskStatusId, employee_id = :employeeId, creator_id = :creatorId, is_assignment = :isAssignment, " +
            "task_comment = :comment " +
            "WHERE task_id = :taskId", nativeQuery = true)
    void patchTask(@Param("taskId") Long taskId, @Param("taskName") String taskName, @Param("taskProjectId") Long taskProjectId,
                   @Param("taskStageId") Long taskStageId, @Param("taskCompanyId") Long taskCompanyId,
                   @Param("taskDate") Date taskDate,
                   @Param("taskTime") Time taskTime, @Param("taskPlace") String taskPlace, @Param("taskResult") String taskResult,
                   @Param("taskDescription") String taskDescription, @Param("taskStatusId") Long taskStatusId,
                   @Param("employeeId") Long employeeId, @Param("creatorId") Long creatorId, @Param("isAssignment") boolean isAssignment,
                   @Param("comment") String comment);

    @Query(value = "SELECT * FROM task WHERE employee_id  = :employeeId and task_date  = :date", nativeQuery = true)
    List<Task> getTasksByDate(@Param("employeeId") Long employeeId, @Param("date") Date date);

    @Query(value = "SELECT * FROM task WHERE task_project_id  = :projectId", nativeQuery = true)
    List<Task> getTasksByProjectId(@Param("projectId") Long projectId);
    @Modifying
    @Query(value = "DELETE FROM task WHERE task_stage_id  = :stageId", nativeQuery = true)
    void deleteTasksByStageId(@Param("stageId") Long stageId);
    @Modifying
    @Query(value = "DELETE FROM task WHERE contact_id  = :contactId", nativeQuery = true)
    void deleteTasksByContactId(@Param("contactId") Long contactId);

    @Query(value = "UPDATE task SET task_result = :taskResult WHERE task_id = :taskId", nativeQuery = true)
    void updateResult(@Param("taskId") Long taskId, @Param("taskResult") String taskResult);

    @Modifying
    @Query(value = "UPDATE task SET contact_id = :contactId WHERE task_id = :taskId", nativeQuery = true)
    void updateContactPerson(@Param("taskId") Long taskId, @Param("contactId") Long contactId);
    /**
     * Запрос для получения задачи по id.
     * @param taskId - id адачи.
     * @return задача.
     */
    @Query(value = "SELECT * FROM task WHERE task_id  = :taskId", nativeQuery = true)
    Task getTaskById(@Param("taskId") Long taskId);

    @Query(value = "SELECT * FROM task WHERE employee_id  = :employeeId and task_date  < :date and (task_status_id = 1 or task_status_id = 4)", nativeQuery = true)
    List<Task> findOverdueTasks(@Param("employeeId") Long employeeId, @Param("date") Date date);

    @Query(value = "SELECT * FROM task WHERE is_assignment = true AND task_project_id = :projectId", nativeQuery = true)
    List<Task> findProjectTasks(@Param("projectId") Long projectId);

    @Query(value = "select employee_id, count(*) from task " +
            "where is_assignment = true and task_status_id = 1" +
            "group by employee_id", nativeQuery = true)
    List<Object[]> getTasksCount();

    @Query(value = "SELECT * FROM task WHERE creator_id = :creatorId AND task_status_id = 2", nativeQuery = true)
    List<Task> getWaitingList(@Param("creatorId") Long creatorId);

    @Query(value = "SELECT * FROM task WHERE creator_id = :creatorId AND task_project_id = :projectId AND task_status_id = 2", nativeQuery = true)
    List<Task> getWaitingListByProjectId(@Param("creatorId") Long creatorId, @Param("projectId") Long projectId);

    @Query(value = "SELECT count(distinct employee_id) + count(distinct creator_id) FROM task " +
            "WHERE task_project_id = :projectId AND employee_id != creator_id", nativeQuery = true)
    int getMemberCount(@Param("projectId") Long projectId);

    @Modifying
    @Query(value = "UPDATE task SET end_date = :endDate, end_time = :endTime WHERE task_id = :taskId", nativeQuery = true)
    void updateEndDate(@Param("taskId") Long taskId, @Param("endDate") Date endDate, @Param("endTime") Time endTime);

    @Query(value = "SELECT task_status_id, count(*) from task " +
           "WHERE is_assignment = true and ((end_date >= current_date - :period and (task_status_id = 3 or task_status_id = 4)) " +
            "or (task_date >= current_date - :period and task_status_id = 1)) " +
           "GROUP BY task_status_id", nativeQuery = true)
    List<Object[]> getTaskCount(@Param("period") int period);
}
