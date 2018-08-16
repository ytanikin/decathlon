package com.decathlon.model;

import java.util.Objects;

public class AthletePoint {

    private String name;
    private int points;
    private String place;

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @SuppressWarnings("unused")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AthletePoint that = (AthletePoint) o;
        return points == that.points &&
                Objects.equals(name, that.name) &&
                Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, points, place);
    }

    @Override
    public String toString() {
        return "AthletPoint{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", place='" + place + '\'' +
                '}';
    }
}
