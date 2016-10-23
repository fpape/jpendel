package be.jpendel.actions.event;

import be.jpendel.application.EventDTO;
import be.jpendel.domain.event.EventRepository;

import java.util.List;
import java.util.stream.Collectors;


public class OverviewOfEvents {
    private EventRepository eventRepository;

    private OverviewOfEvents(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> overview() {
        return eventRepository.getAll().stream().map(e -> EventDTO
                .newBuilder()
                .withLocation(e.getLocation())
                .withName(e.getName())
                .withStartDateTime(e.getStartDateTime()).build()
        ).collect(Collectors.toList());
    }

    public static OverviewOfEvents with(EventRepository eventRepository) {
        return new OverviewOfEvents(eventRepository);
    }
}
