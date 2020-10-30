package com.ues.crm_backend.Models.ContactPerson;

public class ContactPerson{

    private Long contactPersonId;
    private String contactName;
    private Long companyId;
    private String position;
    private boolean makeDecision;
    private String email;
    private String[] notes;
    private Long creatorId;
    private Long lastUpdaterId;

    public ContactPerson(SerializedContactPerson serializedContactPerson){
        this.contactPersonId = serializedContactPerson.getContactPersonId();
        this.contactName = serializedContactPerson.getContactName();
        this.companyId = serializedContactPerson.getCompanyId();
        this.position = serializedContactPerson.getPosition();
        this.makeDecision = serializedContactPerson.getMakeDecision();
        this.email = serializedContactPerson.getEmail();

        this.notes = serializedContactPerson.getNotes().split("¥");

        this.creatorId = serializedContactPerson.getCreatorId();
        this.lastUpdaterId = serializedContactPerson.getLastUpdaterId();
    }

    public Long getContactPersonId() {
        return contactPersonId;
    }
    public String getContactName() {
        return contactName;
    }
    public Long getCompanyId() {
        return companyId;
    }
    public String getPosition() {
        return position;
    }
    public boolean getMakeDecision(){
        return makeDecision;
    }
    public String getEmail() {
        return email;
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
}
