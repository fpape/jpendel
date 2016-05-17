package be.jpendel.application;

import be.jpendel.domain.event.Event;
import be.jpendel.domain.event.EventFactory;
import be.jpendel.domain.event.EventRepository;

import java.util.Collection;

public class EventApplicationService {

    private final EventRepository eventRepository;
    private final EventFactory eventFactory;

    public EventApplicationService(EventRepository eventRepository, EventFactory eventFactory) {
        this.eventRepository = eventRepository;
        this.eventFactory = eventFactory;
    }

    public void create(CreateEventCommand createEventCommand) {
        eventRepository.add(createEvent(createEventCommand));
    }

    public Collection<Event> overview() {
        return eventRepository.getAll();
    }

    private Event createEvent(CreateEventCommand createEventCommand) {
        return eventFactory.create(createEventCommand);
    }
}
