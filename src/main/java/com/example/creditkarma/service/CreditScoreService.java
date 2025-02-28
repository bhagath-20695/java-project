package com.example.creditkarma.service;

import com.example.creditkarma.model.CreditScore;
import com.example.creditkarma.repository.CreditScoreRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CreditScoreService {
    private final CreditScoreRepository creditScoreRepository;

    public CreditScoreService(CreditScoreRepository creditScoreRepository) {
        this.creditScoreRepository = creditScoreRepository;
    }

    public CreditScore getCreditScore(String userId) {
        return creditScoreRepository.findByUserId(Long.valueOf(userId))
                .orElseGet(() -> {
                    // ✅ Use existing `userId` from database, do not create a new one
                    CreditScore newScore = new CreditScore();
                    newScore.setUserId(Long.valueOf(userId));
                    newScore.setScore(new Random().nextInt(551) + 300);
                    return creditScoreRepository.save(newScore);
                });
    }
}
