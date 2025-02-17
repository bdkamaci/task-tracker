package com.bdkamaci.tasktracker.repository;

import com.bdkamaci.tasktracker.model.ToDo;
import com.bdkamaci.tasktracker.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findByStatus(Status status);
}
