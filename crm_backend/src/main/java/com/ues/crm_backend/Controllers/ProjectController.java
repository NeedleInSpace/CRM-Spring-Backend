package com.ues.crm_backend.Controllers;

import com.ues.crm_backend.DataBase.Repositories.ProjectRepository;
import com.ues.crm_backend.Models.Project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
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
        List<Project> projects = projectRepository.getAllProjects();

        return projects != null && !projects.isEmpty()
                ? new ResponseEntity<>(projects, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(name = "id") Long id){
        Project project = projectRepository.getProject(id);
        return project != null
                ? new ResponseEntity<>(project, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/projects")
    public ResponseEntity<?> addNewProject(@RequestBody Project project) {
        project.setStartDate(new Date());
        project.setStagesNumber(0);
        project.setMemberNumber(0);
        Project createdProject = projectRepository.addNewProject(project);
        return createdProject != null
                ? new ResponseEntity<>(createdProject, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/projects")
    public ResponseEntity<?> changeProject(@RequestBody Project project){
        try {
            projectRepository.patchProject(project);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable(name="id") Long id){
        try {
            projectRepository.deleteProject(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
