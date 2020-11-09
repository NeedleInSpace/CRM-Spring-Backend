package com.ues.crm_backend.Models.Stage;

import com.ues.crm_backend.Models.Project.Project;

import javax.persistence.*;

@Entity
@Table(name = "stage")
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stage_id")
    private Long id;

    @Column(name = "stage_name")
    String stageName;
    @ManyToOne
    @JoinColumn(name = "project_id")
    Project project;

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

    public Project getProject() {
        return project;
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

    public void setProject(Project project) {
        this.project = project;
    }
}
