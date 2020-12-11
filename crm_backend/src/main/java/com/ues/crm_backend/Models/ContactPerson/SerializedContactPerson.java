package com.ues.crm_backend.Models.ContactPerson;

import com.ues.crm_backend.Models.Company.Company;
import com.ues.crm_backend.Models.Company.SerializedCompany;

import javax.persistence.*;

/**
 * Сериализуемы класс модели SerializedContactPerson.
 *
 * Причины наличия двух классов для одной модели - читать в readme.
 *
 * @see com.ues.crm_backend.Models.ContactPerson.ContactPerson;
 */
@Entity
@Table(name = "contact_person")
public class SerializedContactPerson {

    /**
     * Все поля являются отображением столбцов из таблицы contact_person.
     */
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

    /**
     * Конструктор, создающий экземпляр SerializedContactPerson на основе объекта ContactPerson.
     * @param contactPerson - сериализуемая версия SerializedContactPerson.
     */
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

    /** Метод для конкатенации массива в строку с разделителем */
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

    public String getContactName() {
        return contactName;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public Company getCompany() {
        if (this.company != null)
            return new Company(this.company);
        else return null;
    }

    public String getPosition() {
        return position;
    }

    public boolean getMakeDecision() {
        return makeDecision;
    }

    public String getMainEmail() {
        return mainEmail;
    }

    public String getOtherEmails() {
        return otherEmails;
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

    public Long getLastUpdaterId() {
        return lastUpdaterId;
    }

    public Long getMainPhone() {
        return mainPhone;
    }

    public String getOtherPhones() {
        return otherPhones;
    }
}
