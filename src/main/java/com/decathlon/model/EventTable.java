package com.decathlon.model;

public enum EventTable {

    RUN_100M(25.437, 18.0, 1.81, EventType.TRACK),
    LONG_JUMP(0.14354, 220, 1.40, EventType.FIELD),
    SHOT_PUT(51.39, 1.5, 1.05, EventType.FIELD),
    HIGH_JUMP(0.8465, 75, 1.42, EventType.FIELD),
    RUN_400M(1.53775, 82, 1.81, EventType.TRACK),
    RUN_100M_HURDLES(5.74352, 28.5, 1.92, EventType.TRACK),
    DISCUS_THROW(12.91, 4.0, 1.1, EventType.FIELD),
    POLE_VAULT(0.2797, 100, 1.35, EventType.FIELD),
    JAVELIN_THROW(10.14, 7.0, 1.08, EventType.FIELD),
    RUN_1500M(0.03768, 480, 1.85, EventType.TRACK);

    private final double a;
    private final double b;
    private final double c;
    private final EventType eventType;

    EventTable(double a, double b, double c, EventType eventType) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.eventType = eventType;
    }

    public int calculatePoint(double performance) {
        if (eventType == EventType.TRACK) {
            return calculateTrackPoint(performance);
        }
        return calculateFieldPoint(performance);
    }

    private int calculateTrackPoint(double performance) {
        return (int) Math.floor(a * Math.pow((b - performance), c));
    }

    private int calculateFieldPoint(double performance) {
        return (int) Math.floor(a * Math.pow((performance - b), c));
    }

    private enum EventType {
        TRACK, FIELD
    }
}
