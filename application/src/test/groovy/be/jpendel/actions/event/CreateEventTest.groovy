package be.jpendel.actions.event

import be.jpendel.application.CreateEventCommand
import be.jpendel.domain.event.Event
import be.jpendel.domain.event.EventRepository
import spock.lang.Specification

import java.time.LocalDateTime

class CreateEventTest extends Specification {
    EventRepository eventRepository = Mock(EventRepository);
    CreateEvent createEvent = new CreateEvent(eventRepository);

    def "Creating a correct event adds an event to the repository"() {
        def now = LocalDateTime.now()
        given:
        CreateEventCommand aNewEvent = CreateEventCommand.builder()
                .withName("name")
                .withLocation("location")
                .withStartDateTime(now)
                .build();
        Event resultEvent=null;

        when:
        createEvent.create(aNewEvent)

        then:
        1 * eventRepository.add(_) >> { arguments -> resultEvent = arguments[0] }
        resultEvent.name == "name"
        resultEvent.location == "location"
        resultEvent.startDateTime == now

    }
}
