package be.jpendel.domain.event;

import be.jpendel.application.CreateEventCommand;

public class EventFactory {
    //TODO add check that event with same name and same startdate, location can not be created
    //->check EventFactoryTest
    public Event create(CreateEventCommand createEventCommand) {
        return new Event(createEventCommand.name, createEventCommand.startDateTime, createEventCommand.location);
    }
}
