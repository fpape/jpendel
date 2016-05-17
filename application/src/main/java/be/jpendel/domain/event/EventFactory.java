package be.jpendel.domain.event;

import be.jpendel.application.CreateEventCommand;

public class EventFactory {
    public Event create(CreateEventCommand createEventCommand) {
        return new Event(createEventCommand.name, createEventCommand.startDateTime, createEventCommand.location);
    }
}
