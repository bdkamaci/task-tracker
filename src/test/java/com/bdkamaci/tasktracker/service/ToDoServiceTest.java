package com.bdkamaci.tasktracker.service;

import com.bdkamaci.tasktracker.dto.ToDoRequest;
import com.bdkamaci.tasktracker.dto.ToDoResponse;
import com.bdkamaci.tasktracker.model.ToDo;
import com.bdkamaci.tasktracker.model.enums.Status;
import com.bdkamaci.tasktracker.repository.ToDoRepository;
import com.bdkamaci.tasktracker.service.impl.ToDoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {
    @Mock
    private ToDoRepository toDoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ToDoServiceImpl toDoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddToDo() {
        ToDoRequest request = new ToDoRequest();
        request.setDescription("Test Description");

        ToDo toDo = new ToDo();
        toDo.setId(1L);
        toDo.setDescription("Test Description");
        toDo.setStatus(Status.TODO);

        ToDoResponse response = new ToDoResponse();
        response.setId(1L);
        response.setDescription("Test Description");
        response.setStatus(Status.TODO.toString());

        when(modelMapper.map(request, ToDo.class)).thenReturn(toDo);
        when(toDoRepository.save(toDo)).thenReturn(toDo);
        when(modelMapper.map(toDo, ToDoResponse.class)).thenReturn(response);

        ToDoResponse result = toDoService.addToDo(request);

        assertEquals(response, result);
        verify(toDoRepository).save(toDo);
    }

    @Test
    void testUpdateToDo() {
        Long id = 1L;
        ToDoRequest request = new ToDoRequest();
        request.setDescription("Updated Description");

        ToDo toDo = new ToDo();
        toDo.setId(id);
        toDo.setDescription("Old Description");
        toDo.setStatus(Status.TODO);

        ToDo updatedToDo = new ToDo();
        updatedToDo.setId(id);
        updatedToDo.setDescription("Updated Description");
        updatedToDo.setStatus(Status.TODO);

        ToDoResponse response = new ToDoResponse();
        response.setId(id);
        response.setDescription("Updated Description");
        response.setStatus(Status.TODO.toString());

        when(toDoRepository.findById(id)).thenReturn(Optional.of(toDo));
        when(toDoRepository.save(toDo)).thenReturn(updatedToDo);
        when(modelMapper.map(updatedToDo, ToDoResponse.class)).thenReturn(response);

        ToDoResponse result = toDoService.updateToDo(id, request);

        assertEquals(response, result);
        verify(toDoRepository).save(toDo);
    }

    @Test
    void testDeleteToDo() {
        Long id = 1L;
        ToDo toDo = new ToDo();
        toDo.setId(id);
        toDo.setDescription("Description");
        toDo.setStatus(Status.TODO);

        when(toDoRepository.findById(id)).thenReturn(Optional.of(toDo));

        toDoService.deleteToDo(id);

        verify(toDoRepository).delete(toDo);
    }

    @Test
    void testMarkInProgress() {
        Long id = 1L;
        ToDo toDo = new ToDo();
        toDo.setId(id);
        toDo.setDescription("Description");
        toDo.setStatus(Status.TODO);

        ToDo updatedToDo = new ToDo();
        updatedToDo.setId(id);
        updatedToDo.setDescription("Description");
        updatedToDo.setStatus(Status.IN_PROGRESS);

        ToDoResponse response = new ToDoResponse();
        response.setId(id);
        response.setDescription("Description");
        response.setStatus(Status.IN_PROGRESS.toString());

        when(toDoRepository.findById(id)).thenReturn(Optional.of(toDo));
        when(toDoRepository.save(toDo)).thenReturn(updatedToDo);
        when(modelMapper.map(updatedToDo, ToDoResponse.class)).thenReturn(response);

        ToDoResponse result = toDoService.markInProgress(id);

        assertEquals(response, result);
        verify(toDoRepository).save(toDo);
    }

    @Test
    void testMarkDone() {
        Long id = 1L;
        ToDo toDo = new ToDo();
        toDo.setId(id);
        toDo.setDescription("Description");
        toDo.setStatus(Status.IN_PROGRESS);

        ToDo updatedToDo = new ToDo();
        updatedToDo.setId(id);
        updatedToDo.setDescription("New Description");
        updatedToDo.setStatus(Status.COMPLETED);

        ToDoResponse response = new ToDoResponse();
        response.setId(id);
        response.setDescription("Description");
        response.setStatus(Status.COMPLETED.toString());

        when(toDoRepository.findById(id)).thenReturn(Optional.of(toDo));
        when(toDoRepository.save(toDo)).thenReturn(updatedToDo);
        when(modelMapper.map(updatedToDo, ToDoResponse.class)).thenReturn(response);

        ToDoResponse result = toDoService.markDone(id);

        assertEquals(response, result);
        verify(toDoRepository).save(toDo);
    }
}
