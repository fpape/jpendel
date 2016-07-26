package be.jpendel.application;

import com.google.common.base.Preconditions;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class CreateEventCommand {
    public final String name;
    public final String description;
    public final String location;
    public final int distance;
    public final Period expectedDuration;
    public final LocalDateTime startDateTime;
    public final LocalDateTime endDateTime;

    private CreateEventCommand(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.location = builder.location;
        this.distance = builder.distance;
        this.expectedDuration = builder.expectedDuration;
        this.startDateTime = builder.startDateTime;
        this.endDateTime = builder.endDateTime;
    }


    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder {
        private String description;
        private String name;
        private String location;
        private int distance;
        private Period expectedDuration;
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder withDistance(int distance) {
            this.distance = distance;
            return this;
        }

        public Builder withExpectedDuration(Period expectedDuration) {
            this.expectedDuration = expectedDuration;
            return this;
        }

        public Builder withStartDateTime(Date startDateTime) {
            this.startDateTime = LocalDateTime.ofInstant(startDateTime.toInstant(), ZoneId.systemDefault());
            return this;
        }

        public Builder withStartDateTime(LocalDateTime startDateTime) {
            this.startDateTime = startDateTime;
            return this;
        }

        public Builder withEndDateTime(LocalDateTime endDateTime) {
            this.endDateTime = endDateTime;
            return this;
        }

        public CreateEventCommand build() {
            validate();
            return new CreateEventCommand(this);
        }

        private void validate() {
            Preconditions.checkNotNull(name, "Name not specified.");
            Preconditions.checkNotNull(location, "End location not specified.");
            Preconditions.checkNotNull(startDateTime, "Start data not specified.");
        }
    }
}
