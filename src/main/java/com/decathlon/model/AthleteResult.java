package com.decathlon.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class AthleteResult {

    private final String name;
    private final Map<EventTable, Double> results = new EnumMap<>(EventTable.class);

    public AthleteResult(String name) {
        this.name = name;
    }

    public void addEventScore(EventTable event, Double score) {
        results.put(event, score);
    }

    public String getName() {
        return name;
    }

    public int points() {
        return results.entrySet().stream()
                .mapToInt(e -> e.getKey().calculatePoint(e.getValue()))
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AthleteResult that = (AthleteResult) o;

        if (!Objects.equals(name, that.name)) return false;
        return results.equals(that.results);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + results.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AthleteResult{" +
                "name='" + name + '\'' +
                ", results=" + results +
                '}';
    }
}
