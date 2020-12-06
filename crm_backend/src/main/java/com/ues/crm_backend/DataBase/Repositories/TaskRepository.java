package com.ues.crm_backend.DataBase.Repositories;

import com.ues.crm_backend.DataBase.Interfaces.ICompanyRepository;
import com.ues.crm_backend.DataBase.Interfaces.ITaskRepository;
import com.ues.crm_backend.Models.Task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
public class TaskRepository {

    @Autowired
    ITaskRepository taskRepository;

    public List<Task> getAllEmployeeTasks(Long id) {
        return taskRepository.getAllEmployeeTasks(id);
    }

    public void addNewTask(Task task) {
        taskRepository.save(task);
    }

    @Transactional
    public void patchTask(Task task) throws Exception {
        try {
            taskRepository.patchTask(
                    task.getTaskId(), task.getTaskName(), task.getTaskProjectId(), task.getTaskStageId(),
                    task.getTaskCompanyId(), task.getContactId(), task.getTaskDate(), task.getTaskTime(),
                    task.getTaskPlace(), task.getTaskDescription(), task.getTaskStatusId(), task.getEmployeeId(),
                    task.getEndDate(), task.getEndTime()
            );
        }catch (Exception e){
            throw new Exception("Not Modified");
        }
    }

    public List<Task> getTasksByDate(Long employeeId, Date date) {
        return taskRepository.getTasksByDate(employeeId, date);
    }

    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.getTasksByProjectId(projectId);
    }
}
