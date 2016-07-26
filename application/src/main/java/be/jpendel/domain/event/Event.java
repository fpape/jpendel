package be.jpendel.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public class Event {
    //The UUID is the business UUID. NOT a hibernate persistence db id
    private final UUID uuid;
    private String name;
    private LocalDateTime startDateTime;
    private String location;


    private Event(Builder builder) {
        this.uuid = UUID.randomUUID();
        this.name = builder.name;
        this.startDateTime = builder.startDateTime;
        this.location = builder.location;
    }

    public static Builder newBuilder() {
        return new Builder();
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


    public static final class Builder {
        private String name;
        private LocalDateTime startDateTime;
        private String location;

        private Builder() {
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withStartDateTime(LocalDateTime val) {
            startDateTime = val;
            return this;
        }

        public Builder withLocation(String val) {
            location = val;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}
