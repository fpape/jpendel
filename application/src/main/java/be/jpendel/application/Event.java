package be.jpendel.application;

import java.time.LocalDateTime;

public class Event {
    private final String name;
    private LocalDateTime startDateTime;
    private String location;

    public Event(String name, LocalDateTime startDateTime, String location) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public String getLocation() {
        return location;
    }
}
