package be.jpendel.domain.event;

import be.jpendel.application.CreateEventCommand;
import org.junit.Test;

import java.time.LocalDateTime;

public class EventFactoryTest {

    @Test
    public void eventWithSameNameAndSameStartdateLocationCanNotBeCreated(){
       EventFactory factory = new EventFactory();
        LocalDateTime time = LocalDateTime.now();
        CreateEventCommand eventCmd = CreateEventCommand.builder()
                .withEndDateTime(time).withLocation("loc")
                .withStartDateTime(time)
                .withName("test").build();
        Event e1 = factory.create(eventCmd);
        EventRepository repo = new InMemEventRepository();
        repo.add(e1);
        repo.add(e1);// moet dit falen ?
        //fail("event With Same Name And Same Startdate Location Can Not Be Created");
    }
}