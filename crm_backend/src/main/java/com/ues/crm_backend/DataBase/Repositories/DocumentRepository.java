package com.ues.crm_backend.DataBase.Repositories;

import com.ues.crm_backend.DataBase.Interfaces.IDocumentRepository;
import com.ues.crm_backend.Models.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class DocumentRepository {
    @Autowired
    IDocumentRepository iDocumentRepository;

    public Long createDocument(Document document) {
        return iDocumentRepository.save(document).getDocId();
    }

    public Document getDocumentById(Long id) {
        return iDocumentRepository.findById(id).orElse(null);
    }

    public List<Document> getDocumentsByTaskId(Long taskId) {
        return iDocumentRepository.findDocumentsByTaskId(taskId);
    }
}

