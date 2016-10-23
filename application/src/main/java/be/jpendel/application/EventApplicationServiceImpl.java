package be.jpendel.application;

import be.jpendel.actions.event.CreateEvent;
import be.jpendel.actions.event.OverviewOfEvents;
import be.jpendel.domain.event.EventRepository;

import java.util.Collection;

public class EventApplicationServiceImpl implements EventApplicationService {


    private final CreateEvent createEvent;
    private final OverviewOfEvents overviewOfEvents;

    public EventApplicationServiceImpl(EventRepository eventRepository) {
        this.createEvent = CreateEvent.with(eventRepository);
        this.overviewOfEvents = OverviewOfEvents.with(eventRepository);

    }

    @Override
    public void create(CreateEventCommand createEventCommand) {
        createEvent.create(createEventCommand);
    }

    @Override
    public Collection<EventDTO> overview() {
        return overviewOfEvents.overview();
    }


}
