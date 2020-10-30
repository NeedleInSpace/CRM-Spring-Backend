package com.ues.crm_backend.Models.Company;

import javax.persistence.*;


public class Company {
    private Long companyId;
    private String name;
    private String kindOfActivity;
    private Long consumptionVolume;
    private boolean generatingCapacity;
    private Long INN;
    private Long KPP;
    private Long OKPO;
    private String email;
    private Long phone;
    private Long creatorId;
    private Long changerId;
    private String[] notes;

    public Company(SerializedCompany serializedCompany){
        this.companyId = serializedCompany.getCompanyId();
        this.name = serializedCompany.getName();
        this.kindOfActivity = serializedCompany.getKindOfActivity();
        this.consumptionVolume = serializedCompany.getConsumptionVolume();
        this.generatingCapacity = serializedCompany.getGeneratingCapacity();
        this.INN = serializedCompany.getINN();
        this.KPP = serializedCompany.getKPP();
        this.OKPO = serializedCompany.getOKPO();
        this.email = serializedCompany.getEmail();
        this.phone = serializedCompany.getPhone();
        this.creatorId = serializedCompany.getCreatorId();
        this.changerId = serializedCompany.getChangerId();

        this.notes = serializedCompany.getNotes().split("¥");
    }

    public Long getCompanyId() {
        return companyId;
    }
    public String getName() {
        return name;
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
    public Long getINN() {
        return INN;
    }
    public Long getKPP() {
        return KPP;
    }
    public Long getOKPO() {
        return OKPO;
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
