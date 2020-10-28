package com.ues.crm_backend.DataBase.Repositories;

import com.ues.crm_backend.DataBase.Interfaces.ITaskRepository;
import com.ues.crm_backend.Models.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskRepository implements ITaskRepository {

    @Override
    public void Insert(Task task) {

    }

    @Override
    public Task Select(int id) {
        return null;
    }

    @Override
    public List<Task> Select() {
        return null;
    }

    @Override
    public void Update(int id, Task task) {

    }
}
