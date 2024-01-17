package com.example.demo.controller;

import com.example.demo.dto.TrainingResult;
import com.example.demo.model.TrainingDetails;
import com.example.demo.model.User;
import com.example.demo.repository.TrainingDetailsRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ITrainingService;
import com.example.demo.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/api/training")
@RequiredArgsConstructor
public class TrainingController {

    private final ITrainingService trainingService;
    private final UserRepository userRepository;
    private final TrainingDetailsRepository trainingDetailsRepository;

    @GetMapping("/multiple-trainings")
    public List<TrainingResult> getUsersWithMultipleTrainingsOnSameDay() {
        return trainingService.getUsersWithMultipleTrainingsOnSameDay();
    }
    @GetMapping("/addData")
    public Integer addData() {
        trainingDetailsRepository.save(new TrainingDetails(1L,1L,1L,new Date()));
        trainingDetailsRepository.save(new TrainingDetails(2L,1L,2L,new Date()));
        trainingDetailsRepository.save(new TrainingDetails(3L,1L,3L,new Date()));
        return 200;
    }
    @GetMapping("/users")
    public List<User> get() {
        return userRepository.findAll();
    }
}
