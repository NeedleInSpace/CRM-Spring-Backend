package com.ues.crm_backend.DataBase.Repositories;

import com.ues.crm_backend.DataBase.Interfaces.IProjectRepository;
import com.ues.crm_backend.DataBase.Interfaces.IStageRepository;
import com.ues.crm_backend.Models.Project.Project;
import com.ues.crm_backend.Models.Stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProjectRepository {

    IProjectRepository iProjectRepository;
    @Autowired
    StageRepository stageRepository;

    @Autowired
    public ProjectRepository(IProjectRepository iProjectRepository, IStageRepository iStageRepository) {
        this.iProjectRepository = iProjectRepository;
    }

    public List<Project> getAllProjects(){
        return iProjectRepository.findAll();
    }

    public Project getProject(Long id){
        return iProjectRepository.findProjectById(id);
    }

    public Project addNewProject (Project project) {
        return iProjectRepository.save(project);
    }

    public List<Project> getProjectsOfManager(Long employeeId) {
        return iProjectRepository.findProjectsOfManager(employeeId);
    }

    @Transactional
    public void patchProject(Project project) throws Exception {
        try {
            iProjectRepository.updateProject(project.getId(), project.getShortName(), project.getFullName(),
                    project.getStagesNumber(), project.getMemberNumber(), project.getDescription());
        }catch (Exception e){
            throw new Exception("Not Modified");
        }
    }

    public void deleteProject(Long id) throws Exception {
        try {
            List<Stage> projectStages = stageRepository.getProjectStages(id);
            if(projectStages != null)
                for (Stage projectStage :projectStages)
                    stageRepository.deleteStageById(projectStage.getId());
            Project project = iProjectRepository.findProjectById(id);
            iProjectRepository.delete(project);
        }catch (Exception e){
            throw new Exception("Not Found");
        }

    }
}
