package com.bdkamaci.tasktracker.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ToDoResponse {
    private Long id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
