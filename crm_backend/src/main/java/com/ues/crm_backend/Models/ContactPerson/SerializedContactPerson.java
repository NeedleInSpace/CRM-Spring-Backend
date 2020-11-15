package com.ues.crm_backend.Models.ContactPerson;

import com.ues.crm_backend.Models.Company.Company;
import com.ues.crm_backend.Models.Company.SerializedCompany;

import javax.persistence.*;

@Entity
@Table(name = "contact_person")
public class SerializedContactPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactPersonId;
    @Column(name = "contact_name")
    private String contactName;
    @Column(name = "contact_company_id")
    private Long companyId;
    @ManyToOne
    @JoinColumn(name = "contact_company_id", insertable = false, updatable = false)
    private SerializedCompany company;
    @Column(name = "contact_position")
    private String position;
    @Column(name = "make_decision")
    private boolean makeDecision;
    @Column(name = "main_email")
    private String mainEmail;
    @Column(name = "other_emails")
    private String otherEmails;
    @Column(name = "contact_note")
    private String notes;
    @Column(name = "creator_id")
    private Long creatorId;
    @Column(name = "last_updater_id")
    private Long lastUpdaterId;
    @Column(name = "main_phone")
    private Long mainPhone;
    @Column(name = "other_phones")
    private String otherPhones;

    public SerializedContactPerson() {}

    public SerializedContactPerson(ContactPerson contactPerson){
        this.contactPersonId = contactPerson.getContactPersonId();
        this.contactName = contactPerson.getContactName();
        this.companyId = contactPerson.getCompanyId();
        this.company = contactPerson.getCompany();
        this.position = contactPerson.getPosition();
        this.makeDecision = contactPerson.getMakeDecision();

        this.notes = contactPerson.getNotes() != null ? concatArray(contactPerson.getNotes()) : null;

        this.mainEmail = contactPerson.getMainEmail();
        this.otherEmails = contactPerson.getOtherEmails() != null ? concatArray(contactPerson.getOtherEmails()) : null;

        this.creatorId = contactPerson.getCreatorId();
        this.lastUpdaterId = contactPerson.getLastUpdaterId();
        this.mainPhone = contactPerson.getMainPhone();

        this.otherPhones = contactPerson.getOtherPhones() != null ? concatArray(contactPerson.getOtherPhones()) : null;
    }

    private String concatArray(String[] array){
        if (array.length == 0){
            return null;
        }

        StringBuilder builder = new StringBuilder();

        for(String note : array) {
            builder.append(note);
            builder.append('¥');
        }

        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    public void addNewNote(String newNote){
        setNotes(notes + "¥" + newNote);
    }

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
        return this.companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Company getCompany() {
        if (this.company != null)
            return new Company(this.company);
        else return null;
    }
    public void setCompany(SerializedCompany company) {
        this.company = company;
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

    public String getMainEmail() {
        return mainEmail;
    }
    public void setMainEmail(String mainEmail) {
        this.mainEmail = mainEmail;
    }

    public String getOtherEmails() {
        return otherEmails;
    }
    public void setOtherEmails(String otherEmails) {
        this.otherEmails = otherEmails;
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

    public Long getMainPhone() {
        return mainPhone;
    }
    public void setMainPhone(Long mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getOtherPhones() {
        return otherPhones;
    }
    public void setOtherPhones(String otherPhones) {
        this.otherPhones = otherPhones;
    }
}
