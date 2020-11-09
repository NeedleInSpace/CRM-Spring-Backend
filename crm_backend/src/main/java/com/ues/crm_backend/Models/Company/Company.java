package com.ues.crm_backend.Models.Company;


public class Company {
    private Long companyId;
    private String name;
    private String fullName;
    private String kindOfActivity;
    private Long consumptionVolume;
    private boolean generatingCapacity;
    private Long inn;
    private Long kpp;
    private Long okpo;
    private String email;
    private Long phone;
    private Long creatorId;
    private Long changerId;
    private String[] notes;

    public Company() {}

    public Company(SerializedCompany serializedCompany){
        this.companyId = serializedCompany.getCompanyId();
        this.name = serializedCompany.getName();
        this.fullName = serializedCompany.getFullName();
        this.kindOfActivity = serializedCompany.getKindOfActivity();
        this.consumptionVolume = serializedCompany.getConsumptionVolume();
        this.generatingCapacity = serializedCompany.getGeneratingCapacity();
        this.inn = serializedCompany.getInn();
        this.kpp = serializedCompany.getKpp();
        this.okpo = serializedCompany.getOkpo();
        this.email = serializedCompany.getEmail();
        this.phone = serializedCompany.getPhone();
        this.creatorId = serializedCompany.getCreatorId();
        this.changerId = serializedCompany.getChangerId();

        if (serializedCompany.getNotes() == null){
            this.notes = null;
        }
        else this.notes = serializedCompany.getNotes().split("¥");
    }

    public Long getCompanyId() {
        return companyId;
    }
    public String getName() {
        return name;
    }
    public String getFullName() {
        return fullName;
    }
    public String getKindOfActivity() {
        return kindOfActivity;
    }
    public Long getConsumptionVolume() {
        return consumptionVolume;
    }
    public boolean getGeneratingCapacity() {
        return generatingCapacity;
    }
    public Long getInn() {
        return inn;
    }
    public Long getKpp() {
        return kpp;
    }
    public Long getOkpo() {
        return okpo;
    }
    public String getEmail() {
        return email;
    }
    public Long getPhone() {
        return phone;
    }
    public Long getCreatorId() {
        return creatorId;
    }
    public Long getChangerId() {
        return changerId;
    }
    public String[] getNotes() {
        return notes;
    }
}
