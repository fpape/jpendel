package be.jpendel.domain.event;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InMemEventRepository implements EventRepository {
    private final List<Event> events = new ArrayList<>();


    @Override
    public void add(Event event) {
        events.add(event);
    }

    @Override
    public Collection<Event> getAll() {
        return ImmutableList.copyOf(events);
    }
}
