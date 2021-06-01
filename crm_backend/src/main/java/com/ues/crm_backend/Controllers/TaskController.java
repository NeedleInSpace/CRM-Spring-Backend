package com.ues.crm_backend.Controllers;

import com.ues.crm_backend.DataBase.Repositories.TaskRepository;
import com.ues.crm_backend.Models.Task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

@RestController
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping(value = "api/employee/tasks/{id}")
    public ResponseEntity<List<Task>> getAllEmployeeTasks(@PathVariable(name = "id") Long employeeId) {
        try {
            List<Task> tasks = taskRepository.getAllEmployeeTasks(employeeId);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "api/employee/overdue/tasks/{userId}")
    public ResponseEntity<?> getOverdueTasks(@PathVariable("userId") Long userId, @RequestParam("today") Date date) {
        try {
            List<Task> tasks = taskRepository.getOverdue(userId, date);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "api/employee/tasks")
    public ResponseEntity<List<Task>> getEmployeeTasksByDate(@RequestParam Long userId, @RequestParam Date date) {
        try {
            List<Task> tasks = taskRepository.getTasksByDate(userId, date);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "api/project/tasks/{id}")
    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable(name = "id") Long projectId) {
        try {
            List<Task> tasks = taskRepository.getTasksByProjectId(projectId);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("api/tasks")
    public ResponseEntity<?> addNewTask(@RequestBody Task task) {
        try {
            Long taskId = taskRepository.addNewTask(task);
            return new ResponseEntity<>(taskId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("api/tasks")
    public ResponseEntity<?> patchTask(@RequestBody Task task){
        try {
            taskRepository.patchTask(task);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("api/task/result")
    public  ResponseEntity<?> addTaskResult(@RequestBody Task task) {
        try {
            taskRepository.addResult(task);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "api/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(name = "id") Long taskId) {
        try {
            Task task = taskRepository.getTaskById(taskId);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "api/tasks/project/{id}")
    public ResponseEntity<?> getProjectTasks(@PathVariable(name = "id") Long projectId) {
        try {
            List<Task> stageTasks = taskRepository.getTasksByStageId(projectId);
            return new ResponseEntity<>(stageTasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "api/tasks/count")
    public ResponseEntity<?> getManagerTasksCount() {
        try {
            List<Object[]> countMap = taskRepository.getManagerTasksCount();
            return new ResponseEntity<>(countMap, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "api/tasks/waiting/{projectId}")
    public ResponseEntity<?> getProjectTasksWaitingList(@PathVariable Long projectId, @RequestParam Long creatorId) {
        try {
            List<Task> waitingTasks = taskRepository.getProjectWaitingList(creatorId, projectId);
            return new ResponseEntity<>(waitingTasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "api/tasks/waiting")
    public ResponseEntity<?> getAllTasksWaitingList(@RequestParam Long creatorId) {
        try {
            List<Task> waitingTasks = taskRepository.getWaitingList(creatorId);
            return new ResponseEntity<>(waitingTasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Эндпоинт для удаления задачи из БД.
     * @param id - id удаляемой задачи.
     * @return HTTP статус выполненной операции.
     */
    @DeleteMapping(value = "api/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable(name = "id") Long id){
        taskRepository.deleteTask(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
