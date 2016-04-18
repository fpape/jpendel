package be.jpendel.application;

import java.util.Collection;
import java.util.Collections;

public class EventApplicationService {

    private Event event;

    public void create(CreateEventCommand createEventCommand) {
        this.event = new Event(createEventCommand.getName());
    }

    public Collection<Event> overview() {
        return Collections.singletonList(event);
    }
}
