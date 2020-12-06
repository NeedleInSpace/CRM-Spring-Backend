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
    IStageRepository iStageRepository;

    @Autowired
    public ProjectRepository(IProjectRepository iProjectRepository, IStageRepository iStageRepository) {
        this.iProjectRepository = iProjectRepository;
        this.iStageRepository =iStageRepository;
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
            List<Stage> projectStages = iStageRepository.findStagesByProjectId(id);
            if(projectStages != null)
                for (Stage projectStage :projectStages)
                    iStageRepository.delete(projectStage);
            Project project = iProjectRepository.findProjectById(id);
            iProjectRepository.delete(project);
        }catch (Exception e){
            throw new Exception("Not Found");
        }

    }
}
