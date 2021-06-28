package com.ues.crm_backend.Models.Stage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ues.crm_backend.Models.Project.Project;
import com.ues.crm_backend.Models.Task.Task;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stage")
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stage_id")
    private Long id;

    @Column(name = "stage_name")
    String stageName;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "stage_result")
    String result;

    @Column(name = "stage_description")
    String description;

    @Column(name = "stage_number")
    int stageNumber;


    public Stage(){
    }
    public Long getId() {
        return id;
    }

    public String getStageName() {
        return stageName;
    }

    public String getResult() {
        return result;
    }

    public String getDescription() {
        return description;
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }
}
