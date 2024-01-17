package com.example.demo.service;

import com.example.demo.dto.TrainingResult;
import com.example.demo.repository.TrainingDetailsRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingService implements ITrainingService {

    @Autowired
    private TrainingDetailsRepository trainingDetailsRepository;

    public List<TrainingResult> getUsersWithMultipleTrainingsOnSameDay() {
        return trainingDetailsRepository.getUsersWithMultipleTrainingsOnSameDay();
    }


}
