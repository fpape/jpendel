package be.jpendel.application;

import be.jpendel.domain.event.Event;
import be.jpendel.domain.event.EventRepository;

import java.util.Collection;
import java.util.Collections;

public class EventApplicationService {

    private EventRepository eventRepository;

    public EventApplicationService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void create(CreateEventCommand createEventCommand) {
        eventRepository.save(new Event(createEventCommand.getName(), createEventCommand.getStartDateTime(), createEventCommand.getLocation()));
    }

    public Collection<Event> overview() {
        return Collections.unmodifiableCollection(eventRepository.findAll());
    }
}
