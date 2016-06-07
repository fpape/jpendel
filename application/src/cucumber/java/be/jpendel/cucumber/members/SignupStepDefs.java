package be.jpendel.cucumber.members;

import be.jpendel.application.SignupUC;
import be.jpendel.domain.member.Member;
import be.jpendel.domain.member.MemberRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SignupStepDefs {
    private MemberRepository memberRepository = memberRepository();

    private String name;
    private String email;

    @Given("^I enter my name \"([^\"]*)\" and email \"([^\"]*)\"$")
    public void iEnterMyNameAndEmail(String name, String email) throws Throwable {
        this.name = name;
        this.email = email;
    }

    @When("^I enter the registration information into the system$")
    public void iEnterTheRegistrationInformationIntoTheSystem() throws Throwable {
        new SignupUC(memberRepository).signup(getSignupRequest());
    }

    @Then("^The user can be found in the repository$")
    public void theUserCanBeFoundInTheRepository() throws Throwable {
        Optional<Member> memberByEmail = memberRepository.findMemberByEmail(email);
        assertThat(memberByEmail.isPresent(), is(true));
    }

    private MemberRepository memberRepository() {
        return new MemberRepository() {
            private List<Member> members = new ArrayList<>();

            @Override
            public void save(Member member) {
                members.add(member);
            }

            @Override
            public Optional<Member> findMemberByEmail(String email) {
                return members.stream().filter(member -> member.getEmail().equals(email)).findFirst();
            }
        };
    }

    private SignupUC.Request getSignupRequest() {
        return new SignupUC.Request() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getEmail() {
                return email;
            }
        };
    }
}
