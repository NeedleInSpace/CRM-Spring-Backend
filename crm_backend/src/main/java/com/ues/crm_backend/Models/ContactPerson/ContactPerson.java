package com.ues.crm_backend.Models.ContactPerson;

import com.ues.crm_backend.Models.Company.Company;
import com.ues.crm_backend.Models.Company.SerializedCompany;

public class ContactPerson{

    private Long contactPersonId;
    private String contactName;
    private Long companyId;
    private Company company;
    private String companyName;
    private String position;
    private boolean makeDecision;
    private String mainEmail;
    private String[] otherEmails;
    private String[] notes;
    private Long creatorId;
    private Long lastUpdaterId;
    private Long mainPhone;
    private String[] otherPhones;

    public ContactPerson() {}

    public ContactPerson(SerializedContactPerson serContactPerson){
        this.contactPersonId = serContactPerson.getContactPersonId();
        this.contactName = serContactPerson.getContactName();
        this.companyId = serContactPerson.getCompanyId();
        this.company = serContactPerson.getCompany();
        this.position = serContactPerson.getPosition();
        this.makeDecision = serContactPerson.getMakeDecision();

        this.notes = serContactPerson.getNotes() != null ? splitString(serContactPerson.getNotes()) : null;

        this.mainEmail = serContactPerson.getMainEmail();
        this.otherEmails = serContactPerson.getOtherEmails() != null ? splitString(serContactPerson.getOtherEmails()) : null;

        this.creatorId = serContactPerson.getCreatorId();
        this.lastUpdaterId = serContactPerson.getLastUpdaterId();
        this.mainPhone = serContactPerson.getMainPhone();

        this.otherPhones = serContactPerson.getOtherPhones() != null ? splitString(serContactPerson.getOtherPhones()) : null;
    }

    private String[] splitString(String string){
        return string.split("¥");
    }

    public Long getContactPersonId() {
        return contactPersonId;
    }
    public String getContactName() {
        return contactName;
    }
    public Long getCompanyId() {
        return this.companyId;
    }
    public SerializedCompany getCompany() {
        if (this.company != null)
            return new SerializedCompany(this.company);
        else
            return null;
    }
    public String getPosition() {
        return position;
    }
    public boolean getMakeDecision(){
        return makeDecision;
    }
    public String getMainEmail() {
        return mainEmail;
    }
    public String[] getOtherEmails() {
        return otherEmails;
    }
    public String[] getNotes() {
        return notes;
    }
    public Long getCreatorId() {
        return creatorId;
    }
    public Long getLastUpdaterId() {
        return lastUpdaterId;
    }
    public Long getMainPhone() {
        return mainPhone;
    }
    public String[] getOtherPhones() {
        return otherPhones;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
