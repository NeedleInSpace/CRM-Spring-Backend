package com.ues.crm_backend.Models;

import java.util.List;

public class ContactPerson {

    private int contactPersonId;
    private int companyId;
    private String position;
    private String FIO;
    private int solver;
    private String email;
    private String mainPhone;
    private List<String> phones;
    private List<String> notes;

    public int getContactPersonId() {
        return contactPersonId;
    }
    public void setContactPersonId(int contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public int getCompanyId() {
        return companyId;
    }
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public String getFIO() {
        return FIO;
    }
    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getSolver() {
        return solver;
    }
    public void setSolver(int solver) {
        this.solver = solver;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMainPhone() {
        return mainPhone;
    }
    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public List<String> getPhones() {
        return phones;
    }
    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<String> getNotes() {
        return notes;
    }
    public void setNotes(List<String> notes) {
        this.notes = notes;
    }
}
