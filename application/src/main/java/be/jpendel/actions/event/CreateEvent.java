package be.jpendel.actions.event;

import be.jpendel.application.CreateEventCommand;
import be.jpendel.domain.event.Event;
import be.jpendel.domain.event.EventRepository;

public class CreateEvent {

    private final EventRepository eventRepository;

    private CreateEvent(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void create(CreateEventCommand createEventCommand) {
        eventRepository.add(createEvent(createEventCommand));
    }

    private Event createEvent(CreateEventCommand createEventCommand) {
        return Event.newBuilder()
                .withName(createEventCommand.name)
                .withStartDateTime(createEventCommand.startDateTime)
                .withLocation(createEventCommand.location)
                .build();
    }

    public static CreateEvent with(EventRepository eventRepository) {
        return new CreateEvent(eventRepository);
    }
}
