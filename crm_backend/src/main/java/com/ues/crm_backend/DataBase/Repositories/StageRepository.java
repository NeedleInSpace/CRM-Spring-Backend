package com.ues.crm_backend.DataBase.Repositories;

import com.ues.crm_backend.DataBase.Interfaces.IProjectRepository;
import com.ues.crm_backend.DataBase.Interfaces.IStageRepository;
import com.ues.crm_backend.Models.Project.Project;
import com.ues.crm_backend.Models.Stage.Stage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StageRepository {

    IStageRepository iStageRepository;
    IProjectRepository iProjectRepository;
    public StageRepository(IStageRepository iStageRepository, IProjectRepository iProjectRepository){
        this.iStageRepository = iStageRepository;
        this.iProjectRepository = iProjectRepository;
    }

    public List<Stage> getProjectStages(Long projectId){
        return iStageRepository.findStagesByProjectId(projectId);
    }
    @Transactional
    public Stage addNewStage(Stage stage) throws Exception {
        try{
            Project project = iProjectRepository.findProjectById(stage.getProjectId());
            project.setStagesNumber(project.getStagesNumber()+1);
            return iStageRepository.save(stage);
        }catch (Exception e){
            throw new Exception("NotFound");
        }
    }

    @Transactional
    public void patchStage(Stage stage){
         iStageRepository.updateStage(stage.getId().intValue(), stage.getStageName(),
                stage.getResult(), stage.getDescription(), stage.getStageNumber());
    }

    @Transactional
    public void deleteStageById(Long id) throws Exception {
        try {
            Stage stage = iStageRepository.findStageById(id);
            Project project = iProjectRepository.findProjectById(stage.getProjectId());
            project.setStagesNumber(project.getStagesNumber()-1);
            iStageRepository.delete(stage);
        }catch (Exception e){
            throw new Exception();
        }


    }
}

