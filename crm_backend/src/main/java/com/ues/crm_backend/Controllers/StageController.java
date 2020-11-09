package com.ues.crm_backend.Controllers;

import com.ues.crm_backend.DataBase.Repositories.StageRepository;
import com.ues.crm_backend.Models.Stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class StageController {
    StageRepository stageRepository;

    @Autowired
    public StageController(StageRepository stageRepository){
        this.stageRepository = stageRepository;
    }

    @GetMapping("/stages/project/{id}")
    public ResponseEntity<List<Stage>> getStages(@PathVariable(name = "id") Long id)
    {
        List<Stage> projectStages = stageRepository.getProjectStages(id);

        return projectStages != null && !projectStages.isEmpty()
                ? new ResponseEntity<>(projectStages, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/stages")
    public ResponseEntity<?> addNewStage(@RequestBody Stage newStage) {
        try {
            Stage createdStage = stageRepository.addNewStage(newStage);
            return  new ResponseEntity<>(createdStage, HttpStatus.CREATED);
        } catch (Exception e ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/stages")
    public ResponseEntity<?> changeStage(@RequestBody Stage stage){
        try {
            stageRepository.patchStage(stage);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

    }
    @DeleteMapping("/stages/{id}")
    public ResponseEntity<?> deleteStage(@PathVariable(name="id") Long stageId){
        try {
            stageRepository.deleteStageById(stageId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
