package be.jpendel.domain.event;

import java.util.Collection;

public interface EventRepository {
    void add(Event event);

    Collection<Event> getAll();
}
