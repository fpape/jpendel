package be.jpendel.cucumber.events;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sun.security.util.PendingException;

public class AssignRoleStepDefs {
    @Given("^an event with uuid \"([^\"]*)\"$")
    public void an_event_with_uuid(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^a person with uuid \"([^\"]*)\"$")
    public void a_person_with_uuid(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the user assigns the role <\"([^\"]*)\"> in the event \"([^\"]*)\" to the person \"([^\"]*)\"$")
    public void the_user_assigns_the_role_in_the_event_to_the_person(String arg1, String arg2, String arg3) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the event \"([^\"]*)\" contains the person \"([^\"]*)\" with the role <\"([^\"]*)\">$")
    public void the_event_contains_the_person_with_the_role(String arg1, String arg2, String arg3) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the user assigns the roles$")
    public void the_user_assigns_the_roles(DataTable arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        throw new PendingException();
    }

    @Then("^the event \"([^\"]*)\" has the roles assigned$")
    public void the_event_has_the_roles_assigned(String arg1, DataTable arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        throw new PendingException();
    }


}
