package com.decathlon.repository;

import com.decathlon.model.AthleteResult;

import java.util.List;

public interface ResultRepository {
    List<AthleteResult> getResults();
}
