package be.jpendel.actions.event

import be.jpendel.domain.event.Event
import be.jpendel.domain.event.EventRepository
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class OverviewOfEventsTest extends Specification {
    def mockEventRepository = Mock(EventRepository) {
        getAll() >> makeEvents()
    }
    @Subject
    def underTest = OverviewOfEvents.with(mockEventRepository)

    List<Event> makeEvents() {
        (0..9).collect {
            Event.newBuilder()
                    .withLocation("location" + it)
                    .withName("name" + it)
                    .withStartDateTime(LocalDateTime.now())
                    .build()
        }
    }

    def "Overview"() {
        when:
        def result = underTest.overview()
        then:
        result.size() == 10

    }

}