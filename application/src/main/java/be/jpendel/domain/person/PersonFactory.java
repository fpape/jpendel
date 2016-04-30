package be.jpendel.domain.person;

import be.jpendel.application.CreatePersonCommand;

import java.util.UUID;

public class PersonFactory {


    public Person create(CreatePersonCommand command) {
        final Person person = new Person(UUID.randomUUID());
        person.setBirthDate(command.birthDate);
        person.setFirstName(command.firstName);
        person.setLastName(command.lastName);
        person.setPhone(command.phone);
        return person;
    }
}
