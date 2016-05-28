package be.jpendel.cucumber.persons;

import be.jpendel.application.CreatePersonCommand;
import be.jpendel.application.PersonApplicationService;
import be.jpendel.application.PersonDTO;
import be.jpendel.domain.person.InMemPersonRepository;
import be.jpendel.domain.person.PersonFactory;
import com.google.common.collect.ImmutableSetMultimap;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static be.jpendel.application.GuavaCollectors.toImmutableSetMap;
import static java.time.ZoneId.systemDefault;
import static org.assertj.core.api.Assertions.assertThat;

public class CreatePersonStepDefinitions {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final PersonApplicationService personApplicationService = new PersonApplicationService(new PersonFactory(),new InMemPersonRepository());

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
        final ImmutableSetMultimap<String, PersonDTO> mappedByName = groupDTOByName(all);
        final ImmutableSetMultimap<String, PersonBean> expectedPersonsGroupedByName = groupBeanByName(beanList);
        expectedPersonsGroupedByName.asMap().entrySet().stream()
                .forEach(x -> isPresent(x, mappedByName));
    }

    private ImmutableSetMultimap<String, PersonBean> groupBeanByName(List<PersonBean> beanList) {
        return beanList.stream()
                .collect(toImmutableSetMap(PersonFunctions::getUniqueName, Function.identity()));
    }

    private ImmutableSetMultimap<String, PersonDTO> groupDTOByName(List<PersonDTO> all) {
        return all.stream()
                .collect(toImmutableSetMap(PersonFunctions::getUniqueName, Function.identity()));
    }

    private void isPresent(Map.Entry<String, Collection<PersonBean>> samePersons, ImmutableSetMultimap<String, PersonDTO> mappedByName) {
        final String uniqueName = samePersons.getKey();
        assertThat(mappedByName.containsKey(uniqueName)).isTrue();

        final Collection<PersonDTO> personDTOs = mappedByName.get(uniqueName);
        final Collection<PersonBean> personBeans = samePersons.getValue();

        checkPersonDataIsEqual(personDTOs, personBeans);

    }

    private void checkPersonDataIsEqual(Collection<PersonDTO> personDTOs, Collection<PersonBean> personBeans) {
        assertThat(personDTOs).hasSameSizeAs(personBeans);
        personBeans.stream().forEach(x -> isEqualToAll(x, personDTOs));
    }

    private void isEqualToAll(PersonBean x, Collection<PersonDTO> personDTOs) {
        personDTOs.stream().forEach(y -> isEqual(x, y));
    }

    private void isEqual(PersonBean x, PersonDTO personDTO) {
        assertThat(x.getFirstName()).isEqualTo(personDTO.firstName);
        assertThat(x.getLastName()).isEqualTo(personDTO.lastName);
        assertThat(toDate(x.getBirthDate())).isEqualTo(personDTO.birthDate);
        assertThat(x.getPhone()).isEqualTo(personDTO.phone);
        assertThat(personDTO.uuid).isNotNull();
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
