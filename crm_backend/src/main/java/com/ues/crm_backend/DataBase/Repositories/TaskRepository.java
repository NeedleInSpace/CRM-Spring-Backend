package com.ues.crm_backend.DataBase.Repositories;

import com.ues.crm_backend.DataBase.Interfaces.IEmployeeRepository;
import com.ues.crm_backend.DataBase.Interfaces.IProjectRepository;
import com.ues.crm_backend.DataBase.Interfaces.ITaskRepository;
import com.ues.crm_backend.Models.Project.Project;
import com.ues.crm_backend.Models.Task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskRepository {

    @Autowired
    ITaskRepository taskRepository;
    @Autowired
    IEmployeeRepository employeeRepository;

    @Autowired
    IProjectRepository projectRepository;

    public List<Task> getAllEmployeeTasks(Long id) {
        return taskRepository.getAllEmployeeTasks(id);
    }

    public Long addNewTask(Task task) {
        /* if(task.getEndDate() == null) {
            task.setEndDate(task.getTaskDate());
            task.setEndTime(task.getTaskTime());
        } */
        Project currProject = projectRepository.findProjectById(task.getTaskProjectId());
        currProject.setMemberNumber(taskRepository.getMemberCount(task.getTaskProjectId()));
        projectRepository.save(currProject);
        return taskRepository.save(task).getTaskId();
    }

    public List<Task> getOverdue(Long userId, Date date) {
        return taskRepository.findOverdueTasks(userId, date);
    }

    @Transactional
    public void patchTask(Task task) throws Exception {
        try {
            System.out.println(task.getContactId());
            if (task.getContactId() != null) {
                taskRepository.updateContactPerson(task.getTaskId(), task.getContactId());
            }
            if(task.getTaskStatusId() == 3 || task.getTaskStatusId() == 4) {
               task.setEndDate(new Date(new java.util.Date().getTime()));
               task.setEndTime(new Time(new java.util.Date().getTime()));
               taskRepository.updateEndDate(task.getTaskId(), task.getEndDate(), task.getEndTime());
            }
            taskRepository.patchTask(
                    task.getTaskId(), task.getTaskName(), task.getTaskProjectId(), task.getTaskStageId(),
                    task.getTaskCompanyId(), task.getTaskDate(), task.getTaskTime(),
                    task.getTaskPlace(), task.getResult(), task.getTaskDescription(), task.getTaskStatusId(), task.getEmployeeId(),
                    task.getCreatorId(), task.getIsAssignment(), task.getComment()
            );
            Project currProject = projectRepository.findProjectById(task.getTaskProjectId());
            currProject.setMemberNumber(taskRepository.getMemberCount(task.getTaskProjectId()));
            projectRepository.save(currProject);
        }catch (Exception e){
            throw new Exception("Not Modified");
        }
    }

    public List<Task> getProjectWaitingList(Long creatorId, Long projectId) throws Exception {
        try {
            return taskRepository.getWaitingListByProjectId(creatorId, projectId);
        } catch (Exception e) {
            throw new Exception("Waiting list not received");
        }
    }

    public List<Task> getWaitingList(Long creatorId) throws Exception {
        try {
            return taskRepository.getWaitingList(creatorId);
        } catch (Exception e) {
            throw new Exception("Waiting list not received");
        }
    }

    @Transactional
    public void addResult(Task task) throws Exception {
        try {
            taskRepository.updateResult(task.getTaskId(), task.getResult());
        } catch (Exception e) {
            throw new Exception("Not Modified");
        }
    }

    public List<Task> getTasksByDate(Long employeeId, Date date) {
        return taskRepository.getTasksByDate(employeeId, date);
    }

    public List<Task> getTasksByStageId(Long projectId) {
        return taskRepository.findProjectTasks(projectId);
    }
    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.getTasksByProjectId(projectId);
    }

    public List<Object[]> getManagerTasksCount() {
        return  taskRepository.getTasksCount();
    }
    /**
     * Обработчик эндпоинта getTaskById.
     * @param taskId - id интересующей задачи.
     * @return искомая задача.
     */
    public Task getTaskById(Long taskId){
        Task task = taskRepository.getTaskById(taskId);

        if (task == null) return null;

        return task;
    }

    /**
     * Обработчик эндпоинта deleteTask.
     * @param taskId - id удаляемой задачи.
     */
    public void deleteTask(Long taskId){
        Task task = taskRepository.getTaskById(taskId);

        if (task == null) return;

        taskRepository.delete(task);
    }

    public Map<Object, Object> getTasksCountStatus() {
        ArrayList<BigInteger> day = getMap(taskRepository.getTaskCount(0));
        ArrayList<BigInteger>  week = getMap(taskRepository.getTaskCount(7));

        ArrayList<BigInteger> month = getMap(taskRepository.getTaskCount(30));
        ArrayList<BigInteger> year = getMap(taskRepository.getTaskCount(365));
        ArrayList<BigInteger> all = getMap(taskRepository.getTaskCount(36500));
        Map<Object, Object> response = new HashMap<>();
        response.put("label", "Задачи");
        response.put("day", day);
        response.put("week", week);
        response.put("month", month);
        response.put("year", year);
        response.put("all", all);
        return response;
    }

    public Map<Object, Object> getTasksCountByProject(Long projectId) {
        ArrayList<BigInteger> day = getMap(taskRepository.getTaskCountByProject(0, projectId));
        ArrayList<BigInteger>  week = getMap(taskRepository.getTaskCountByProject(7, projectId));

        ArrayList<BigInteger> month = getMap(taskRepository.getTaskCountByProject(30, projectId));
        ArrayList<BigInteger> year = getMap(taskRepository.getTaskCountByProject(365, projectId));
        ArrayList<BigInteger> all = getMap(taskRepository.getTaskCountByProject(36500, projectId));
        Map<Object, Object> response = new HashMap<>();
        response.put("label", "Задачи");
        response.put("day", day);
        response.put("week", week);
        response.put("month", month);
        response.put("year", year);
        response.put("all", all);
        return response;
    }

    public Map<Object, Object> getTasksCountByEmployee(Long employeeId) {
        ArrayList<BigInteger> day = getMap(taskRepository.getTaskCountByEmployee(0, employeeId));
        ArrayList<BigInteger>  week = getMap(taskRepository.getTaskCountByEmployee(7, employeeId));

        ArrayList<BigInteger> month = getMap(taskRepository.getTaskCountByEmployee(30, employeeId));
        ArrayList<BigInteger> year = getMap(taskRepository.getTaskCountByEmployee(365, employeeId));
        ArrayList<BigInteger> all = getMap(taskRepository.getTaskCountByEmployee(36500, employeeId));
        Map<Object, Object> response = new HashMap<>();
        response.put("label", "Задачи");
        response.put("day", day);
        response.put("week", week);
        response.put("month", month);
        response.put("year", year);
        response.put("all", all);
        return response;
    }

    private ArrayList<BigInteger> getMap(List<Object[]> periodData) {
        ArrayList<BigInteger> counts = new ArrayList<>();
        for(int i=1; i<=4;i++){
            boolean flag = false;
            if(i==2)
                continue;
            for(Object[] taskCount:periodData) {
                if((int)taskCount[0]==i) {
                    counts.add((BigInteger) taskCount[1]);
                    flag = true;
                }
            }
            if(!flag)
                counts.add(BigInteger.ZERO);
        }
        return counts;
    }

}
