package com.decathlon.repository;

import com.decathlon.model.AthletePoints;

public interface ScoreRepository {
    void save(AthletePoints athletePoints);
}
