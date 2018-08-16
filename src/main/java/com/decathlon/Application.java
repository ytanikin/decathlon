package com.decathlon;

import com.decathlon.model.AthletePoints;
import com.decathlon.repository.ScoreRepository;
import com.decathlon.repository.ResultRepository;
import com.decathlon.model.AthleteResult;
import com.decathlon.service.PointCalculationService;

import java.util.List;

public class Application {

    private final ResultRepository resultRepository;
    private final ScoreRepository scoreRepository;
    private final PointCalculationService calculationService;

    public Application(ResultRepository resultRepository, ScoreRepository scoreRepository, PointCalculationService calculationService) {
        this.resultRepository = resultRepository;
        this.scoreRepository = scoreRepository;
        this.calculationService = calculationService;
    }

    public void run() {
        List<AthleteResult> results = resultRepository.getResults();
        AthletePoints points = calculationService.calculate(results);
        scoreRepository.save(points);
    }
}
