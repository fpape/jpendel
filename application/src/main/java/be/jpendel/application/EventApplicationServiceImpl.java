package be.jpendel.application;

import be.jpendel.domain.event.Event;
import be.jpendel.domain.event.EventFactory;
import be.jpendel.domain.event.EventRepository;

import java.util.Collection;
import java.util.stream.Collectors;

public class EventApplicationServiceImpl implements EventApplicationService {

    private final EventRepository eventRepository;
    private final EventFactory eventFactory;

    public EventApplicationServiceImpl(EventRepository eventRepository, EventFactory eventFactory) {
        this.eventRepository = eventRepository;
        this.eventFactory = eventFactory;
    }

    @Override public void create(CreateEventCommand createEventCommand) {
        eventRepository.add(createEvent(createEventCommand));
    }

    @Override public Collection<EventDTO> overview() {

        return eventRepository.getAll().stream().map(e->EventDTO
                .newBuilder()
                .withLocation(e.getLocation())
                .withName(e.getName())
                .withStartDateTime(e.getStartDateTime()).build()
        ).collect(Collectors.toList());
    }

    private Event createEvent(CreateEventCommand createEventCommand) {
        return eventFactory.create(createEventCommand);
    }
}
