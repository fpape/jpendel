package be.jpendel.actions.event

import be.jpendel.domain.event.Event
import be.jpendel.domain.event.EventRepository
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class OverviewOfEventsTest extends Specification {
    def mockEventRepository = Mock(EventRepository)
//    EventRepository mockEventRepository = Mock()

    @Subject
    def underTest = OverviewOfEvents.with(mockEventRepository)

    List<Event> makeEvents() {
        (0..9).collect {
            Event.newBuilder()
                    .withLocation("location: $it")
                    .withName("name: $it")
                    .withStartDateTime(LocalDateTime.now())
                    .build()
        }
    }

    def "Overview"() {
        given:
        mockEventRepository.getAll() >> makeEvents()
        when:
        def result = underTest.overview()
        then:
        result.size() == 10
        result.every { it.location.startsWith('location: ') }
        result.every { it.location ==~ /location: \d/ }
        result.every { it.name.startsWith('name: ') }
        result.every { it.name ==~ /name: \d/ }
        result.every { it.startDateTime }
    }

}