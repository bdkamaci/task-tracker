package com.bdkamaci.tasktracker.controller;

import com.bdkamaci.tasktracker.dto.ToDoRequest;
import com.bdkamaci.tasktracker.dto.ToDoResponse;
import com.bdkamaci.tasktracker.model.enums.Status;
import com.bdkamaci.tasktracker.service.ToDoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoControllerTest {
    @Mock
    private ToDoService toDoService;

    @InjectMocks
    private ToDoController toDoController;

    public ToDoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddToDo() {
        ToDoRequest request = new ToDoRequest();
        request.setDescription("Description");

        ToDoResponse response = new ToDoResponse();
        response.setId(1L);
        response.setDescription("Description");
        response.setStatus(Status.TODO.toString());

        when(toDoService.addToDo(request)).thenReturn(response);

        ResponseEntity<ToDoResponse> result = toDoController.addToDo(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void testUpdateToDo() {
        Long id = 1L;
        ToDoRequest request = new ToDoRequest();
        request.setDescription("Updated Description");

        ToDoResponse response = new ToDoResponse();
        response.setId(id);
        response.setDescription("Updated Description");
        response.setStatus(Status.TODO.toString());

        when(toDoService.updateToDo(id, request)).thenReturn(response);

        ResponseEntity<ToDoResponse> result = toDoController.updateToDo(id, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void testDeleteToDo() {
        Long id = 1L;

        ResponseEntity<Void> result = toDoController.deleteToDo(id);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(toDoService).deleteToDo(id);
    }

    @Test
    void testMarkInProgress() {
        Long id = 1L;
        ToDoResponse response = new ToDoResponse();
        response.setId(id);
        response.setDescription("Description");
        response.setStatus(Status.IN_PROGRESS.toString());

        when(toDoService.markInProgress(id)).thenReturn(response);

        ResponseEntity<ToDoResponse> result = toDoController.markInProgress(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void testListAll() {
        ToDoResponse response = new ToDoResponse();
        response.setId(1L);
        response.setDescription("Description");
        response.setStatus(Status.TODO.toString());

        List<ToDoResponse> toDoList = List.of(response);

        when(toDoService.listAll()).thenReturn(toDoList);

        ResponseEntity<List<ToDoResponse>> result = toDoController.listAll();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(toDoList, result.getBody());
    }
}
