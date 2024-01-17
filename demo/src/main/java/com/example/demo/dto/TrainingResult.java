package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingResult {
    private Long userId;
    private String username;
    private Long trainingId;
    private java.sql.Timestamp trainingDate;
    private Long count;
}
