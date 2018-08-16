package com.decathlon.service;

import com.decathlon.model.AthletePoint;
import com.decathlon.model.AthletePoints;
import com.decathlon.model.AthleteResult;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.decathlon.model.EventTable.*;
import static com.decathlon.model.EventTable.RUN_1500M;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PointCalculationServiceImplTest {

    private static final String TEST_NAME_0 = "test name0";
    private static final String TEST_NAME_1 = "test name1";
    private static final String TEST_NAME_2 = "test name2";
    private static final String TEST_NAME_3 = "test name3";
    private final PointCalculationService calculationService = new PointCalculationServiceImpl();;

    @Test
    void testGetAthletePointsThreeAthleteTheSamePlace() {
        AthletePoints actualAthletePoints = calculationService.calculate(getResultsTheSameScore());
        assertEquals(getExpectedTheSamePoints(), actualAthletePoints);
    }

    @Test
    void testGetAthletePointsWithNoTheSamePlace() {
        List<AthleteResult> results = new ArrayList<>();
        results.add(getAthleteResult(TEST_NAME_0, 43.3, (double) 62, 23.2, (double) 93, 2.90, 8.22, 9.83, (double) 6, 6.45, 8.63));
        AthletePoints actualAthletePoints = calculationService.calculate(results);
        AthletePoints expectedAthletePoints = new AthletePoints(singletonList(getAthletePoint(TEST_NAME_0, 10814, "1")));
        assertEquals(expectedAthletePoints, actualAthletePoints);
    }

    private List<AthleteResult> getResultsTheSameScore() {
        List<AthleteResult> results = new ArrayList<>();
        results.add(getAthleteResult(TEST_NAME_0, 43.3, (double) 62, 23.2, (double) 93, 2.90, 8.22, 9.83, (double) 6, 6.45, 8.63));
        results.add(getAthleteResult(TEST_NAME_1, 43.3, (double) 62, 23.2, (double) 93, 2.90, 8.22, 9.83, (double) 6, 6.45, 8.63));
        results.add(getAthleteResult(TEST_NAME_2, 43.3, (double) 62, 23.2, (double) 93, 2.90, 8.22, 9.83, (double) 6, 6.45, 8.63));
        results.add(getAthleteResult(TEST_NAME_3, 403.3, (double) 92, 23.2, (double) 93, 200.90, 8.22, 9.83, (double) 6, 6.45, 8.63));
        return results;
    }

    private AthletePoints getExpectedTheSamePoints() {
        List<AthletePoint> points = new ArrayList<>();
        points.add(getAthletePoint(TEST_NAME_0, 10814, "1-2-3"));
        points.add(getAthletePoint(TEST_NAME_1, 10814, "1-2-3"));
        points.add(getAthletePoint(TEST_NAME_2, 10814, "1-2-3"));
        points.add(getAthletePoint(TEST_NAME_3, 6621, "4"));
        return new AthletePoints(points);
    }

    protected AthletePoint getAthletePoint(String name, int points, String place) {
        AthletePoint athletePoint = new AthletePoint();
        athletePoint.setName(name);
        athletePoint.setPoints(points);
        athletePoint.setPlace(place);
        return athletePoint;
    }

    protected AthleteResult getAthleteResult(String name,
                                             Double running100mSec,
                                             Double longJumpCm,
                                             Double shotPutMeter,
                                             Double highJumpCm,
                                             Double running400mSec,
                                             Double running100mHurdlesSec,
                                             Double discusThrowMeter,
                                             Double poleVaultCm,
                                             Double javelinThrowMeter,
                                             Double running1500mSec
    ) {
        AthleteResult athleteResult = new AthleteResult(name);
        athleteResult.addEventScore(RUN_100M, running100mSec);
        athleteResult.addEventScore(LONG_JUMP, longJumpCm);
        athleteResult.addEventScore(SHOT_PUT, shotPutMeter);
        athleteResult.addEventScore(HIGH_JUMP, highJumpCm);
        athleteResult.addEventScore(RUN_400M, running400mSec);
        athleteResult.addEventScore(RUN_100M_HURDLES, running100mHurdlesSec);
        athleteResult.addEventScore(DISCUS_THROW, discusThrowMeter);
        athleteResult.addEventScore(POLE_VAULT, poleVaultCm);
        athleteResult.addEventScore(JAVELIN_THROW, javelinThrowMeter);
        athleteResult.addEventScore(RUN_1500M, running1500mSec);
        return athleteResult;
    }
}
