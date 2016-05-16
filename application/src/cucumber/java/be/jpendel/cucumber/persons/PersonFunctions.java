package be.jpendel.cucumber.persons;

import be.jpendel.application.PersonDTO;

public class PersonFunctions {

    public static final String UNDER_SCORE = "_";

    private PersonFunctions() {
    }

    public static String getUniqueName(PersonDTO x) {
        return x.firstName + UNDER_SCORE + x.lastName;
    }

    public static String getUniqueName(PersonBean x) {
        return x.getFirstName() + UNDER_SCORE + x.getLastName();
    }
}
