package com.example.demo.repository;


import com.example.demo.dto.TrainingResult;
import com.example.demo.model.TrainingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingDetailsRepository extends JpaRepository<TrainingDetails, Long> {
    @Query(value ="""
            SELECT new com.example.demo.dto.TrainingResult(U.userId, U.username, TD.trainingId, TD.trainingDate, COUNT(*))
            FROM User U 
            INNER JOIN TrainingDetails TD ON U.userId = TD.userId
            GROUP BY U.userId, U.username, TD.trainingId, TD.trainingDate
            """)
    List<TrainingResult> getUsersWithMultipleTrainingsOnSameDay();
}
