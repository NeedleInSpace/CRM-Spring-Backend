package com.ues.crm_backend.DataBase.Interfaces;

import com.ues.crm_backend.Models.Document;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IDocumentRepository extends CrudRepository<Document, Long> {

    @Query(value = "SELECT * FROM documents WHERE task_id  = :taskId", nativeQuery = true)
    List<Document>  findDocumentsByTaskId(@Param("taskId") Long taskId);
}
