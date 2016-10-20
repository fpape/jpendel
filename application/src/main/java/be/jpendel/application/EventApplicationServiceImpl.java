package be.jpendel.application;

import be.jpendel.domain.event.Event;
import be.jpendel.domain.event.EventRepository;

import java.util.Collection;
import java.util.stream.Collectors;

public class EventApplicationServiceImpl implements EventApplicationService, EventQueryRepository {

    private final EventRepository eventRepository;

    public EventApplicationServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void create(CreateEventCommand createEventCommand) {
        eventRepository.add(createEvent(createEventCommand));
    }

    @Override
    public Collection<EventDTO> overview() {
        return eventRepository.getAll().stream().map(Event.asEventDTO()).collect(Collectors.toList());
    }

    private Event createEvent(CreateEventCommand createEventCommand) {
        return Event.newBuilder()
                .withName(createEventCommand.name)
                .withStartDateTime(createEventCommand.startDateTime)
                .withLocation(createEventCommand.location)
                .build();
    }
}
