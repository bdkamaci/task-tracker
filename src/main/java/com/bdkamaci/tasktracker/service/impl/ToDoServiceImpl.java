package com.bdkamaci.tasktracker.service.impl;

import com.bdkamaci.tasktracker.dto.ToDoRequest;
import com.bdkamaci.tasktracker.dto.ToDoResponse;
import com.bdkamaci.tasktracker.exception.ResourceNotFoundException;
import com.bdkamaci.tasktracker.model.ToDo;
import com.bdkamaci.tasktracker.model.enums.Status;
import com.bdkamaci.tasktracker.repository.ToDoRepository;
import com.bdkamaci.tasktracker.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {
    private final ToDoRepository toDoRepository;
    private final ModelMapper modelMapper;

    @Override
    public ToDoResponse addToDo(ToDoRequest toDoRequest) {
        ToDo toDo = modelMapper.map(toDoRequest, ToDo.class);
        toDo.setStatus(Status.TODO);
        toDo.setCreatedAt(LocalDateTime.now());
        toDo.setUpdatedAt(LocalDateTime.now());
        ToDo savedToDo = toDoRepository.save(toDo);
        return modelMapper.map(savedToDo, ToDoResponse.class);
    }

    @Override
    public ToDoResponse updateToDo(Long id, ToDoRequest toDoRequest) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ToDo not found with id: " + id));
        toDo.setDescription(toDoRequest.getDescription());
        toDo.setUpdatedAt(LocalDateTime.now());
        ToDo updatedToDo = toDoRepository.save(toDo);
        return modelMapper.map(updatedToDo, ToDoResponse.class);
    }

    @Override
    public void deleteToDo(Long id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ToDo not found with id: " + id));
        toDoRepository.delete(toDo);
    }

    @Override
    public ToDoResponse markInProgress(Long id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ToDo not found with id: " + id));
        toDo.setStatus(Status.IN_PROGRESS);
        toDo.setUpdatedAt(LocalDateTime.now());
        ToDo updatedToDo = toDoRepository.save(toDo);
        return modelMapper.map(updatedToDo, ToDoResponse.class);
    }

    @Override
    public ToDoResponse markDone(Long id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ToDo not found with id: " + id));
        toDo.setStatus(Status.COMPLETED);
        toDo.setUpdatedAt(LocalDateTime.now());
        ToDo updatedToDo = toDoRepository.save(toDo);
        return modelMapper.map(updatedToDo, ToDoResponse.class);
    }

    @Override
    public List<ToDoResponse> listAll() {
        List<ToDo> toDos = toDoRepository.findAll();
        return toDos.stream()
                .map(toDo -> modelMapper.map(toDo, ToDoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ToDoResponse> listByStatus(Status status) {
        List<ToDo> toDos = toDoRepository.findByStatus(status);
        return toDos.stream()
                .map(toDo -> modelMapper.map(toDo, ToDoResponse.class))
                .collect(Collectors.toList());
    }
}
