package be.jpendel.domain.event;

import java.time.LocalDateTime;

public class Event {
    private String name;
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
