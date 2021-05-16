package com.ues.crm_backend.Controllers;

import com.ues.crm_backend.DataBase.Repositories.DocumentRepository;
import com.ues.crm_backend.DataBase.Repositories.TaskRepository;
import com.ues.crm_backend.Models.Document;
import com.ues.crm_backend.Models.Task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;

@RestController
public class DocumentController {
    @Autowired
    public TaskRepository taskRepository;
    @Autowired
    private DocumentRepository documentRepository;

    @PostMapping(value = "api/task/documents/{taskId}")
    public ResponseEntity<?> addDocuments(@PathVariable("taskId") Long taskId, @RequestBody MultipartFile[] files) throws IOException {
        try {
            Task documentTask = taskRepository.getTaskById(taskId);

            for (MultipartFile file : files) {
                Document newDocument = new Document();
                newDocument.setTask(documentTask);
                newDocument.setCompanyId(documentTask.getTaskCompanyId());
                newDocument.setProjectId(documentTask.getTaskProjectId());
                System.out.println(Arrays.toString(file.getBytes()));
                newDocument.setContext(file.getBytes());
                newDocument.setDocumentTitle(file.getOriginalFilename());
                documentRepository.createDocument(newDocument);
            }
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "api/task/documents/{taskId}")
    public ResponseEntity<?> getTaskDocuments(@PathVariable("taskId") Long taskId) {
        try {
            return new ResponseEntity<>( documentRepository.getDocumentsByTaskId(taskId), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "api/documents/{id}")
    public ResponseEntity<?> downloadDocument(@PathVariable("id") Long id) {
        Document document = documentRepository.getDocumentById(id);
        if (document != null) {
            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");

            ByteArrayResource resource = new ByteArrayResource(document.getContext());

            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(document.getContext().length)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        }
        return null;
    }

}
