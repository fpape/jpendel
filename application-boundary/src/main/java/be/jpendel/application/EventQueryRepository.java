package be.jpendel.application;

import java.util.Collection;

/**
 * Created by Mark on 7/26/2016.
 */
public interface EventQueryRepository {
    Collection<EventDTO> overview();
}
