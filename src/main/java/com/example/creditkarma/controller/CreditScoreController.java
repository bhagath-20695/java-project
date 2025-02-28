package com.example.creditkarma.controller;

import com.example.creditkarma.model.CreditScore;
import com.example.creditkarma.service.CreditScoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-score")
public class CreditScoreController {
    private final CreditScoreService creditScoreService;

    public CreditScoreController(CreditScoreService creditScoreService) {
        this.creditScoreService = creditScoreService;
    }

    @GetMapping("/{userId}")
    public CreditScore getCreditScore(@PathVariable String userId) {
        return creditScoreService.getCreditScore(userId);
    }
}
