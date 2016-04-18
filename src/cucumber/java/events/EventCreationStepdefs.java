package events;

import be.jpendel.application.CreateEventCommand;
import be.jpendel.application.Event;
import be.jpendel.application.EventApplicationService;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.Matchers;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class EventCreationStepdefs {

    private EventApplicationService eventApplicationService = new EventApplicationService();

    @When("^An event is created with name \"([^\"]*)\"$")
    public void anEventIsCreatedWithName(String eventName) throws Throwable {
        CreateEventCommand createEventCommand = createEventCommand(eventName);
        eventApplicationService.create(createEventCommand);
    }

    @Then("^The event with name \"([^\"]*)\" is listed in the event overview$")
    public void theEventWithNameIsListedInTheEventOverview(String eventName) throws Throwable {
        Collection<Event> overview = eventApplicationService.overview();
        assertThat(overview, hasSize(1));
        assertThat(getFirstEvent(overview).getName(), is(equalTo(eventName)));
    }

    private Event getFirstEvent(Collection<Event> overview) {
        return overview.stream().findFirst().get();
    }

    @When("^Events are created with following names$")
    public void eventsAreCreatedWithFollowingNames(List<String> eventNames) throws Throwable {
        for (String eventName : eventNames) {
            CreateEventCommand createEventCommand = createEventCommand(eventName);
            eventApplicationService.create(createEventCommand);
        }
    }

    @Then("^Below events are listed in the event overview$")
    public void belowEventsAreListedInTheEventOverview(List<String> eventNames) throws Throwable {
        Collection<Event> overview = eventApplicationService.overview();
        assertThat(overview, hasSize(3));

        assertThat(overview.stream().map(Event::getName).collect(toList()), containsInAnyOrder(eventNames.toArray()));
    }

    private CreateEventCommand createEventCommand(String eventName) {
        return CreateEventCommand.builder()
                .withName(eventName)
                .withLocation("Brussel")
                .withStartDateTime(LocalDateTime.now())
                .build();
    }
}