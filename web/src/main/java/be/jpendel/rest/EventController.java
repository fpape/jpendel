package be.jpendel.rest;

import be.jpendel.application.CreateEventCommand;
import be.jpendel.application.EventApplicationService;
import be.jpendel.domain.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class EventController {

    @Autowired
    private EventApplicationService eventApplicationService;

    @RequestMapping(method = RequestMethod.GET, path = "/event")
    public Collection<Event> overview() {
        return eventApplicationService.overview();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/event")
    public void create(@RequestBody CreateEventCommand.Builder createEventCommandBuilder) {
        eventApplicationService.create(createEventCommandBuilder.build());
    }
}
