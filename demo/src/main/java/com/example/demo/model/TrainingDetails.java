package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TrainingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userTrainingId;
    private Long userId;
    private Long trainingId;
    private Date trainingDate;
}