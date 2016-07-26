package be.jpendel.domain.event;

import org.junit.Test;

import java.time.LocalDateTime;

public class EventFactoryTest {

    @Test
    public void eventWithSameNameAndSameStartdateLocationCanNotBeCreated() {

        final Event e1 = Event.newBuilder()
                .withLocation("loc")
                .withStartDateTime(LocalDateTime.now())
                .withName("test").build();
        final EventRepository repo = new InMemEventRepository();
        repo.add(e1);
        repo.add(e1);//TODO  moet dit falen ?
        //fail("event With Same Name And Same Startdate Location Can Not Be Created");
    }
}