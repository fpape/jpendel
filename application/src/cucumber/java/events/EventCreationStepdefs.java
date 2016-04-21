package events;

import be.jpendel.application.CreateEventCommand;
import be.jpendel.application.Event;
import be.jpendel.application.EventApplicationService;
import cucumber.api.Format;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class EventCreationStepdefs {

    private EventApplicationService eventApplicationService = new EventApplicationService();

    @When("^An event (?:is|was) created with name \"([^\"]*)\"$")
    public void anEventIsCreatedWithName(String eventName) throws Throwable {
        final CreateEventCommand createEventCommand = createEventCommand(eventName);
        eventApplicationService.create(createEventCommand);
    }

    @When("^An event (?:is|was) created with name \"([^\"]*)\" for date \"([^\"]*)\" in location \"([^\"]*)\"$")
    public void an_event_is_created_with_name_for_date_in_location(String eventName, @Format("dd-MM-yyyy") Date date, String location) throws Throwable {
        final CreateEventCommand command = CreateEventCommand.builder()
                .withName(eventName)
                .withLocation(location)
                .withStartDateTime(map(date))
                .build();
        eventApplicationService.create(command);

    }

    @Then("^The event with name \"([^\"]*)\" is listed in the event overview$")
    public void theEventWithNameIsListedInTheEventOverview(String eventName) throws Throwable {
        the_event_with_name_is_listed_times_in_the_event_overview(eventName, 1);
    }

    @Then("^The event with name \"([^\"]*)\" is listed (\\d+) times in the event overview$")
    public void the_event_with_name_is_listed_times_in_the_event_overview(String eventName, int eventOccurrences) throws Throwable {
        Collection<Event> overview = eventApplicationService.overview();
        assertThat(overview, hasSize(eventOccurrences));
        assertThat(getFirstEvent(overview).getName(), is(equalTo(eventName)));
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

    private LocalDateTime map(@Format("dd-MM-yyyy") Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private Event getFirstEvent(Collection<Event> overview) {
        return overview.stream().findFirst().get();
    }

    private CreateEventCommand createEventCommand(String eventName) {
        return CreateEventCommand.builder()
                .withName(eventName)
                .withLocation("Brussel")
                .withStartDateTime(LocalDateTime.now())
                .build();
    }
}