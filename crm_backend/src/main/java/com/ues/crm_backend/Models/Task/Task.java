package com.ues.crm_backend.Models.Task;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="task_id")
    private Long taskId;
    @Column(name ="task_name")
    private String taskName;
    @Column(name ="task_project_id")
    private Long taskProjectId;
    @Column(name ="task_stage_id")
    private Long taskStageId;
    @Column(name ="task_company_id")
    private Long taskCompanyId;
    @Column(name ="contact_id")
    private Long contactId;
    @Column(name ="task_date")
    private Date taskDate;
    @Column(name ="task_time")
    private Time taskTime;
    @Column(name ="task_place")
    private String taskPlace;
    @Column(name ="task_description")
    private String taskDescription;
    @Column(name ="task_status_id")
    private Long taskStatusId;
    @Column(name ="employee_id")
    private Long employeeId;
    @Column(name ="end_date")
    private Date endDate;
    @Column(name ="end_time")
    private Time endTime;

    public Task() {

    }

    public Long getTaskId() {
        return taskId;
    }
    public String getTaskName() {
        return taskName;
    }
    public Long getTaskProjectId() {
        return taskProjectId;
    }
    public Long getTaskStageId() {
        return taskStageId;
    }
    public Long getTaskCompanyId() {
        return taskCompanyId;
    }
    public Long getContactId() {
        return contactId;
    }
    public Date getTaskDate() {
        return taskDate;
    }
    public Time getTaskTime() {
        return taskTime;
    }
    public String getTaskPlace() {
        return taskPlace;
    }
    public String  getTaskDescription() {
        return taskDescription;
    }
    public Long getTaskStatusId() {
        return taskStatusId;
    }
    public Long getEmployeeId() {
        return employeeId;
    }
    public Date getEndDate() {
        return endDate;
    }
    public Time getEndTime() {
        return endTime;
    }
}
