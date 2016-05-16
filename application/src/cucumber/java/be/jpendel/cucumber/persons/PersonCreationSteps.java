package be.jpendel.cucumber.persons;

import be.jpendel.application.CreatePersonCommand;
import be.jpendel.application.PersonApplicationService;
import be.jpendel.application.PersonDTO;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static java.time.ZoneId.systemDefault;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonCreationSteps {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final PersonApplicationService personApplicationService = new PersonApplicationService();

    @Given("^no persons are present in the system$")
    public void no_persons_are_present_in_the_system() throws Throwable {
        final List<PersonDTO> all = personApplicationService.getAll();
        assertThat(all).isEmpty();
    }

    @When("^(?:a|the) (?:person|persons) (?:is|are) created$")
    public void createPersons(List<PersonBean> personBeen) throws Throwable {
        personBeen.stream()
                .forEach(x -> personApplicationService.createPerson(map(x)));

    }

    @Then("^the (?:person|persons) (?:is|are) listed in the person overview$")
    public void the_person_is_listed_in_the_person_overview(List<PersonBean> beanList) throws Throwable {
        final List<PersonDTO> all = personApplicationService.getAll();
        assertThat(all).hasSameSizeAs(beanList);
    }

    private CreatePersonCommand map(PersonBean personBean) {
        return CreatePersonCommand.newBuilder()
                .withFirstName(personBean.getFirstName())
                .withLastName(personBean.getLastName())
                .withBirthDate(toDate(personBean.getBirthDate()))
                .withPhone(personBean.getPhone())
                .build();
    }


    private LocalDate toDate(Date dateToConvert) {
        final Instant instant = dateToConvert.toInstant();
        final LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, systemDefault());
        return localDateTime.toLocalDate();
    }

    private LocalDate toDate(String dateToConvert) {
        return LocalDate.parse(dateToConvert, DATE_TIME_FORMATTER);
    }
}
