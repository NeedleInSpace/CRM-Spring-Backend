package com.ues.crm_backend.Models;

import javax.persistence.*;

@Entity
@Table(name = "contact_person")
public class ContactPerson{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactPersonId;
    @Column(name = "contact_name")
    private String contactName;
    @Column(name = "contact_company_id")
    private Long companyId;
    @Column(name = "contact_position")
    private String position;
    @Column(name = "make_decision")
    private boolean makeDecision;
    @Column(name = "email")
    private String email;
    @Column(name = "contact_note")
    private String notes;
    @Column(name = "creator_id")
    private Long creatorId;
    @Column(name = "last_updater_id")
    private Long lastUpdaterId;

    public ContactPerson() {}

    public Long getContactPersonId() {
        return contactPersonId;
    }
    public void setContactPersonId(Long contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public boolean getMakeDecision() {
        return makeDecision;
    }
    public void setMakeDecision(boolean makeDecision) {
        this.makeDecision = makeDecision;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getLastUpdaterId() {
        return lastUpdaterId;
    }
    public void setLastUpdaterId(Long lastUpdaterId) {
        this.lastUpdaterId = lastUpdaterId;
    }
}
