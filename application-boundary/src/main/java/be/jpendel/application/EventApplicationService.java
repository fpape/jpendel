package be.jpendel.application;

import java.util.Collection;

/**
 * Created by Mark on 7/26/2016.
 */
public interface EventApplicationService {
    void create(CreateEventCommand createEventCommand);

    Collection<EventDTO> overview();
}
