package com.bdkamaci.tasktracker.dto;

import lombok.Data;

@Data
public class ToDoRequest {
    private String description;
    private String status;
}
