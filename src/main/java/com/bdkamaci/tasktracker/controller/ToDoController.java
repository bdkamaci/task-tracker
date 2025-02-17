package com.bdkamaci.tasktracker.controller;

import com.bdkamaci.tasktracker.dto.ToDoRequest;
import com.bdkamaci.tasktracker.dto.ToDoResponse;
import com.bdkamaci.tasktracker.model.enums.Status;
import com.bdkamaci.tasktracker.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;

    @PostMapping
    public ResponseEntity<ToDoResponse> addToDo(@RequestBody ToDoRequest toDoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoService.addToDo(toDoRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoResponse> updateToDo(@PathVariable Long id, @RequestBody ToDoRequest toDoRequest) {
        return ResponseEntity.ok(toDoService.updateToDo(id, toDoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
        toDoService.deleteToDo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/in-progress")
    public ResponseEntity<ToDoResponse> markInProgress(@PathVariable Long id) {
        return ResponseEntity.ok(toDoService.markInProgress(id));
    }

    @PutMapping("/{id}/done")
    public ResponseEntity<ToDoResponse> markDone(@PathVariable Long id) {
        return ResponseEntity.ok(toDoService.markDone(id));
    }

    @GetMapping
    public ResponseEntity<List<ToDoResponse>> listAll() {
        return ResponseEntity.ok(toDoService.listAll());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ToDoResponse>> listByStatus(@PathVariable Status status) {
        return ResponseEntity.ok(toDoService.listByStatus(status));
    }
}
