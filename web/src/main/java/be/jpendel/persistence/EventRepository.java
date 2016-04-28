package be.jpendel.persistence;

import be.jpendel.domain.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class EventRepository implements be.jpendel.domain.event.EventRepository {

    @Autowired
    private EventMongoRepository eventMongoRepository;

    @Override
    public void add(Event event) {
        eventMongoRepository.save(event);
    }

    @Override
    public Collection<Event> getAll() {
        return eventMongoRepository.findAll();
    }
}
