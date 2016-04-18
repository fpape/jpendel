package be.jpendel.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class EventApplicationService {

    private Collection<Event> events = new ArrayList<>();

    public void create(CreateEventCommand createEventCommand) {
        this.events.add(new Event(createEventCommand.getName()));
    }

    public Collection<Event> overview() {
        return Collections.unmodifiableCollection(events);
    }
}
