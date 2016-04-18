package events;

import be.jpendel.application.CreateEventCommand;
import be.jpendel.application.Event;
import be.jpendel.application.EventApplicationService;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class EventCreationStepdefs {

    private EventApplicationService eventApplicationService = new EventApplicationService();

    @When("^An event is created with name \"([^\"]*)\"$")
    public void anEventIsCreatedWithName(String eventName) throws Throwable {
        CreateEventCommand createEventCommand = createEventCommand(eventName);
        eventApplicationService.create(createEventCommand);
    }

    private CreateEventCommand createEventCommand(String eventName) {
        return CreateEventCommand.builder()
                .withName(eventName)
                .withLocation("Brussel")
                .withStartDateTime(LocalDateTime.now())
                .build();
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

}