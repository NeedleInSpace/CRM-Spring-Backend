package com.ues.crm_backend.Models;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name="documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private long docId;

    @Column(name="doc_name")
    private String documentTitle;

    @Column(name="project_id")
    private Long projectId;

    @Column(name="company_id")
    private Long companyId;

    @Column(name="task_id")
    private Long taskId;

    @Lob
    @Column(name="doc_file")
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] context;

    public Document() {}

    public Document(long docId, Long projectId, Long companyId, Long taskId, byte[] context) {
        this.docId = docId;
        this.projectId = projectId;
        this.companyId = companyId;
        this.taskId = taskId;
    }

    public long getDocId() {
        return docId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public byte[] getContext() {
        return context;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setContext(byte[] context) {
        this.context = context;
    }
}
