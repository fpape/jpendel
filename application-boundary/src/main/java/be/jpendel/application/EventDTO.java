package be.jpendel.application;

import java.time.LocalDateTime;

public class EventDTO {
    private String name;
    private LocalDateTime startDateTime;
    private String location;

    private EventDTO(Builder builder) {
        name = builder.name;
        startDateTime = builder.startDateTime;
        location = builder.location;
    }

    public static Builder newBuilder() {
        return new Builder();
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

        public EventDTO build() {
            return new EventDTO(this);
        }
    }
}
