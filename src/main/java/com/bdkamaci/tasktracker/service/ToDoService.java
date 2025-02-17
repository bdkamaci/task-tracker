package com.bdkamaci.tasktracker.service;

import com.bdkamaci.tasktracker.dto.ToDoRequest;
import com.bdkamaci.tasktracker.dto.ToDoResponse;
import com.bdkamaci.tasktracker.model.enums.Status;

import java.util.List;

public interface ToDoService {
    ToDoResponse addToDo(ToDoRequest toDoRequest);
    ToDoResponse updateToDo(Long id, ToDoRequest toDoRequest);
    void deleteToDo(Long id);
    ToDoResponse markInProgress(Long id);
    ToDoResponse markDone(Long id);
    List<ToDoResponse> listAll();
    List<ToDoResponse> listByStatus(Status status);
}
