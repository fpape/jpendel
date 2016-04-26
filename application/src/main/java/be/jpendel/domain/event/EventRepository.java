package be.jpendel.domain.event;

import java.util.Collection;

public interface EventRepository {
    void save(Event event);

    Collection<Event> findAll();
}
