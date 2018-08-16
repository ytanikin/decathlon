package com.decathlon.service;

import com.decathlon.model.AthletePoint;
import com.decathlon.model.AthletePoints;
import com.decathlon.model.AthleteResult;

import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class PointCalculationServiceImpl implements PointCalculationService {

    @Override
    public AthletePoints calculate(List<AthleteResult> results) {
        List<AthletePoint> points = results.stream()
                .map(this::getAthletePoint)
                .collect(groupingBy(AthletePoint::getPoints, () -> new TreeMap<>(reverseOrder()), toList())).values().stream()
                .peek(calculatePlaceConsumer())
                .flatMap(Collection::stream)
                .collect(toList());

        return new AthletePoints(points);
    }

    private Consumer<List<AthletePoint>> calculatePlaceConsumer() {
        int[] placeCounter = new int[]{0};
        return athletePoints -> {
            String place = calculatePlace(placeCounter, athletePoints);
            athletePoints.forEach(ap -> ap.setPlace(place));
        };
    }

    private String calculatePlace(int[] placeCounter, List<AthletePoint> athletePoints) {
        placeCounter[0]++;
        StringBuilder place = new StringBuilder(Integer.toString(placeCounter[0]));
        for (int i = 0; i < athletePoints.size() - 1; i++) {
            place.append("-").append(++placeCounter[0]);
        }
        return place.toString();
    }

    private AthletePoint getAthletePoint(AthleteResult result) {
        AthletePoint athletePoint = new AthletePoint();
        athletePoint.setName(result.getName());
        athletePoint.setPoints(result.points());
        return athletePoint;
    }

}
