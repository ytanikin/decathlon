package com.decathlon.service;

import com.decathlon.model.AthletePoints;
import com.decathlon.model.AthleteResult;

import java.util.List;

public interface PointCalculationService {
    AthletePoints calculate(List<AthleteResult> results);
}
