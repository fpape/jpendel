package be.jpendel.application;

import be.jpendel.domain.event.Event;
import be.jpendel.domain.event.EventRepository;

import java.util.Collection;

public class EventApplicationService {

    private EventRepository eventRepository;

    public EventApplicationService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void create(CreateEventCommand createEventCommand) {
        eventRepository.add(createEvent(createEventCommand));
    }

    public Collection<Event> overview() {
        return eventRepository.getAll();
    }

    //TODO move this to factory
    private Event createEvent(CreateEventCommand createEventCommand) {
        return new Event(createEventCommand.name, createEventCommand.startDateTime, createEventCommand.location);
    }
}
