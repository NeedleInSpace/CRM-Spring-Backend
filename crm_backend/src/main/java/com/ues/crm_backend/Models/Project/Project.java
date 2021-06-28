package com.ues.crm_backend.Models.Project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ues.crm_backend.Models.Stage.Stage;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="project_id")
    private Long id;

    @Column(name ="project_short_name")
    private String shortName;

    @Column(name ="project_full_name")
    private String fullName;

    @Column(name ="stages_number")
    private int stagesNumber;

    @Column(name ="member_number")
    private int memberNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name ="start_date")
    private Date startDate;

    @Column(name ="project_description")
    private String description;

    /*@JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Stage> projectStages;*/

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public int getStagesNumber() {
        return stagesNumber;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStagesNumber(int stagesNumber) {
        this.stagesNumber = stagesNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }
}
