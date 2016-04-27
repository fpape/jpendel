package be.jpendel.cucumber.events;

import be.jpendel.application.CreateEventCommand;
import be.jpendel.application.EventApplicationService;
import be.jpendel.domain.event.Event;
import be.jpendel.domain.event.EventRepository;
import cucumber.api.Format;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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

  private EventApplicationService eventApplicationService = new EventApplicationService(new EventRepository() {
    private List<Event> events = new ArrayList<>();

    @Override
    public void save(Event event) {
      events.add(event);
    }

    @Override
    public Collection<Event> findAll() {
      return events;
    }
  });

  @When("^An event (?:is|was) created with name \"([^\"]*)\"$")
  public void anEventIsCreatedWithName(String eventName) throws Throwable {
    createEvent(CreateEventCommand.builder()
                                  .withName(eventName)
                                  .withStartDateTime(LocalDateTime.now())
                                  .withLocation("Brussel"));
  }

  @When("^An event (?:is|was) created with name \"([^\"]*)\" for date \"([^\"]*)\" in location \"([^\"]*)\"$")
  public void createEvent(String eventName, @Format("dd-MM-yyyy") Date date, String location) throws Throwable {
    createEvent(CreateEventCommand.builder()
                                  .withName(eventName)
                                  .withStartDateTime(map(date))
                                  .withLocation(location));
  }

  @Then("^The event with name \"([^\"]*)\" is listed in the event overview$")
  public void theEventWithNameIsListedInTheEventOverview(String eventName) throws Throwable {
    timesListedInOverview(eventName, 1);
  }

  @Then("^The event with name \"([^\"]*)\" is listed (\\d+) times in the event overview$")
  public void timesListedInOverview(String eventName, long eventOccurrences) throws Throwable {
    Collection<Event> overview = eventApplicationService.overview();

    assertThat(countEventsWithName(eventName, overview), is(eventOccurrences));
  }

  @When("^Events are created with following names$")
  public void eventsAreCreatedWithFollowingNames(List<String> eventNames) throws Throwable {
    eventNames.forEach((eventName) -> createEvent(CreateEventCommand.builder()
                                                                    .withName(eventName)
                                                                    .withStartDateTime(LocalDateTime.now())
                                                                    .withLocation("Brussel")));
  }

  @Then("^Below events are listed in the event overview$")
  public void belowEventsAreListedInTheEventOverview(List<String> eventNames) throws Throwable {
    Collection<Event> overview = eventApplicationService.overview();

    assertThat(overview, hasSize(eventNames.size()));
    assertThat(overview.stream().map(Event::getName).collect(toList()), containsInAnyOrder(eventNames.toArray()));
  }

  @And("^the event details match \"([^\"]*)\", \"([^\"]*)\"$")
  public void theEventDetailsMatch(@Format("dd-MM-yyyy") Date date, String location) throws Throwable {
    Collection<Event> overview = eventApplicationService.overview();

    assertEventDetails(getFirstEvent(overview), date, location);
  }

  @Given("^An event exists with name \"([^\"]*)\"$")
  public void anEventExistsWithName(String eventName) throws Throwable {
    createEvent(CreateEventCommand.builder()
                                  .withName(eventName)
                                  .withStartDateTime(LocalDateTime.now())
                                  .withLocation("Brussel"));
  }

  private void assertEventDetails(Event event, Date date, String location) {
    assertThat(event.getStartDateTime(), is(equalTo(map(date))));
    assertThat(event.getLocation(), is(equalTo(location)));
  }

  private CreateEventCommand createEvent(CreateEventCommand.Builder builder) {
    CreateEventCommand event = builder.build();
    eventApplicationService.create(event);
    return event;
  }

  private long countEventsWithName(String eventName, Collection<Event> events) {
    return events.stream()
                 .map(Event::getName)
                 .filter(eventName::equals)
                 .count();
  }

  private LocalDateTime map(@Format("dd-MM-yyyy") Date date) {
    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
  }

  private Event getFirstEvent(Collection<Event> overview) {
    return overview.stream().findFirst().get();
  }


}