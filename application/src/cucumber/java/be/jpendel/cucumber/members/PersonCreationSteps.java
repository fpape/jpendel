package be.jpendel.cucumber.members;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonCreationSteps {
    //TODO kan dit ook  naar een enkele DTO ipv een lijst?
    @When("^(?:a|the) (?:person|persons) (?:is|are) created$")
    public void a_person_is_created(List<PersonDTO> personDTOs) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
//        assertEquals(1, arg1.size());
        throw new PendingException();
    }

    @Then("^the (?:person|persons) (?:is|are) listed in the person overview$")
    public void the_person_is_listed_in_the_person_overview(List<PersonDTO> personDTOs) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        assertEquals(1, personDTOs.size());
        throw new PendingException();
    }
}
