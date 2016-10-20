package be.jpendel.rest;

import be.jpendel.application.CreateEventCommand;
import be.jpendel.application.EventApplicationService;
import be.jpendel.application.EventDTO;
import be.jpendel.application.EventQueryRepository;
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

    @Autowired
    private EventQueryRepository eventQueryRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/event")
    public Collection<EventDTO> overview() {
        return eventQueryRepository.overview();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/event")
    public void create(@RequestBody CreateEventCommand.Builder createEventCommandBuilder) {
        eventApplicationService.create(createEventCommandBuilder.build());
    }
}
