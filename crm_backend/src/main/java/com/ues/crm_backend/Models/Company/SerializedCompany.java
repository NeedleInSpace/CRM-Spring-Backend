package com.ues.crm_backend.Models.Company;

import javax.persistence.*;

@Entity
@Table(name = "company")
public class SerializedCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;
    @Column(name = "company_name")
    private String name;
    @Column(name = "company_full_name")
    private String fullName;
    @Column(name = "company_occupation")
    private String kindOfActivity;
    @Column(name = "consumption_volume_id")
    private Long consumptionVolume;
    @Column(name = "generating_capacity")
    private boolean generatingCapacity;
    @Column(name = "inn")
    private Long inn;
    @Column(name = "kpp")
    private Long kpp;
    @Column(name = "okpo")
    private Long okpo;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private Long phone;
    @Column(name = "creator_id")
    private Long creatorId;
    @Column(name = "last_updater_id ")
    private Long changerId;
    @Column(name = "company_note")
    private String notes;

    public SerializedCompany() {}

    public SerializedCompany(Company company){
        this.companyId = company.getCompanyId();
        this.name = company.getName();
        this.fullName = company.getFullName();
        this.kindOfActivity = company.getKindOfActivity();
        this.consumptionVolume = company.getConsumptionVolume();
        this.generatingCapacity = company.getGeneratingCapacity();
        this.inn = company.getInn();
        this.kpp = company.getKpp();
        this.okpo = company.getOkpo();
        this.email = company.getEmail();
        this.phone = company.getPhone();
        this.creatorId = company.getCreatorId();
        this.changerId = company.getChangerId();

        if (company.getNotes() == null){
            this.notes = null;
        }
        else{
            StringBuilder builder = new StringBuilder();
            for(String note : company.getNotes()) {
                builder.append(note);
                builder.append('¥');
            }
            this.notes = builder.deleteCharAt(builder.length() - 1).toString();
        }
    }

    public void addNewNote(String newNote){
        setNotes(notes + "¥" + newNote);
    }

    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getKindOfActivity() {
        return kindOfActivity;
    }
    public void setKindOfActivity(String kindOfActivity) {
        this.kindOfActivity = kindOfActivity;
    }

    public Long getConsumptionVolume() {
        return consumptionVolume;
    }
    public void setConsumptionVolume(Long consumptionVolume) {
        this.consumptionVolume = consumptionVolume;
    }

    public boolean getGeneratingCapacity() {
        return generatingCapacity;
    }
    public void setGeneratingCapacity(boolean generatingCapacity) {
        this.generatingCapacity = generatingCapacity;
    }

    public Long getInn() {
        return inn;
    }
    public void setInn(Long inn) {
        this.inn = inn;
    }

    public Long getKpp() {
        return kpp;
    }
    public void setKpp(Long kpp) {
        this.kpp = kpp;
    }

    public Long getOkpo() {
        return okpo;
    }
    public void setOkpo(Long okpo) {
        this.okpo = okpo;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getChangerId() {
        return changerId;
    }
    public void setChangerId(Long changerId) {
        this.changerId = changerId;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}