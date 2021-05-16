package com.ues.crm_backend.Controllers;

import com.ues.crm_backend.DataBase.Repositories.ProjectRepository;
import com.ues.crm_backend.Models.Project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        try {
            List<Project> projects = projectRepository.getAllProjects();
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(name = "id") Long id){
        try {
            Project project = projectRepository.getProject(id);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/employee/projects/{userId}")
    public ResponseEntity<?> getManagerProjects(@PathVariable("userId") Long userId) {
        try {
            return new ResponseEntity<>(projectRepository.getProjectsOfManager(userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/projects")
    public ResponseEntity<?> addNewProject(@RequestBody Project project) {
        try {
            project.setStartDate(new Date());
            project.setStagesNumber(0);
            project.setMemberNumber(0);
            Project createdProject = projectRepository.addNewProject(project);
            return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/projects")
    public ResponseEntity<?> changeProject(@RequestBody Project project){
        try {
            projectRepository.patchProject(project);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable(name="id") Long id){
        try {
            projectRepository.deleteProject(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
