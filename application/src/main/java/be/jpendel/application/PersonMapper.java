package be.jpendel.application;

import be.jpendel.domain.person.Person;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PersonMapper {

    private PersonMapper() {
    }

    static List<PersonDTO> map(Collection<Person> persons) {
        return persons.stream().map(PersonMapper::map).collect(Collectors.toList());
    }

    static PersonDTO map(Person person) {
        return PersonDTO.newBuilder()
                .withUuid(person.getId())
                .withFirstName(person.getFirstName())
                .withLastName(person.getLastName())
                .withBirthDate(person.getBirthDate())
                .withPhone(person.getPhone())
                .build();
    }
}
