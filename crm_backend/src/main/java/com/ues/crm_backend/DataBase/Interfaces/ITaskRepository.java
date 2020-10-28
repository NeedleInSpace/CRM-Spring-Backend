package com.ues.crm_backend.DataBase.Interfaces;

import com.ues.crm_backend.Models.Task;

import java.util.List;

public interface ITaskRepository {

    public void Insert(Task task);

    public Task Select(int id);

    public List<Task> Select();

    public void Update(int id, Task task);
}
