package com.example.demo.service;

import com.example.demo.dto.TrainingResult;

import java.util.List;

public interface ITrainingService {
    List<TrainingResult> getUsersWithMultipleTrainingsOnSameDay();
}
