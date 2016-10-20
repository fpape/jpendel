package be.jpendel.cucumber.events

import be.jpendel.application.CreateEventCommand
import be.jpendel.application.EventApplicationServiceImpl
import be.jpendel.domain.event.Event
import be.jpendel.domain.event.EventRepository
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

@Subject(EventApplicationServiceImpl)
class EventCreationSpec extends Specification {
    def mockEventRepo = Mock(EventRepository)
    def underTest = new EventApplicationServiceImpl(mockEventRepo);

    @Ignore
    def "adding a null event throws exception"() {
        when:
        underTest.create(null)
        then:
        thrown(IllegalArgumentException)
        and:
        0 * mockEventRepo._
    }

    def "creating command creates an event with correct data"() {
        given:
        def cmd = CreateEventCommand.builder()
                .withName('unittest-name')
                .withStartDateTime(LocalDateTime.now())
                .withLocation('unittest-location')
                .build()
        and:
        Event recordedEvent = null
        when:
        underTest.create(cmd)
        then:
        1 * mockEventRepo.add(_) >> { args -> recordedEvent = args[0] }
        and:
        recordedEvent.@uuid != null
        recordedEvent.name == cmd.name
        recordedEvent.startDateTime == cmd.startDateTime
        recordedEvent.location == cmd.location
    }

}