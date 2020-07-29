package com.decathlon.repository;

import com.decathlon.model.AthleteResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import static com.decathlon.model.EventTable.*;
import static java.lang.Double.parseDouble;
import static java.util.stream.Collectors.toList;

public class ResultRepositoryCsv implements ResultRepository {

    private static final String CSV_DELIMITER = ";";
    private final String path;

    public ResultRepositoryCsv(String path) {
        this.path = path;
    }

    @Override
    public List<AthleteResult> getResults() {
        try (BufferedReader inputStreamClose = Files.newBufferedReader(Paths.get(path))) {
            return inputStreamClose.lines()
                    .map(this::parseAthleteResult)
                    .collect(toList());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private AthleteResult parseAthleteResult(String line) {
        String[] scores = line.split(CSV_DELIMITER);
        checkScoreSize(scores);
        AthleteResult athleteResult = new AthleteResult(scores[0]);
        athleteResult.addEventScore(RUN_100M, parseDouble(scores[1]));
        athleteResult.addEventScore(LONG_JUMP, convertToCentimeters(scores[2]));
        athleteResult.addEventScore(SHOT_PUT, parseDouble(scores[3]));
        athleteResult.addEventScore(HIGH_JUMP, convertToCentimeters(scores[4]));
        athleteResult.addEventScore(RUN_400M, parseDouble(scores[5]));
        athleteResult.addEventScore(RUN_100M_HURDLES, parseDouble(scores[6]));
        athleteResult.addEventScore(DISCUS_THROW, parseDouble(scores[7]));
        athleteResult.addEventScore(POLE_VAULT, convertToCentimeters(scores[8]));
        athleteResult.addEventScore(JAVELIN_THROW, parseDouble(scores[9]));
        athleteResult.addEventScore(RUN_1500M, convertToSeconds(scores[10]));
        return athleteResult;
    }

    private void checkScoreSize(String[] scores) {
        if (scores.length != 11) {
            throw new IllegalArgumentException();
        }
    }

    private double convertToCentimeters(String meters) {
        return parseDouble(meters) * 100;
    }

    private double convertToSeconds(String score) {
        String durationString = "PT" + score.replace(":", "M") + "S";
        return Duration.parse(durationString).getSeconds();
    }
}
