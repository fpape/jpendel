package be.jpendel.application;

import be.jpendel.domain.person.Person;
import be.jpendel.domain.person.PersonFactory;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class PersonApplicationService {
    private ImmutableMap.Builder<UUID, Person> persons = ImmutableMap.builder();
    private PersonFactory personFactory = new PersonFactory();

    public void createPerson(CreatePersonCommand command) {
        checkNotNull(command);
        final Person person = personFactory.create(command);
        persons.put(person.getId(), person);
    }

    public List<PersonDTO> getAll() {
        final ImmutableCollection<Person> persons = this.persons.build().values();
        return map(persons);
    }


    private List<PersonDTO> map(ImmutableCollection<Person> persons) {
        return persons.stream().map(this::map).collect(Collectors.toList());
    }

    private PersonDTO map(Person person) {
        return PersonDTO.newBuilder()
                .withUuid(person.getId())
                .withFirstName(person.getFirstName())
                .withLastName(person.getLastName())
                .withBirthDate(person.getBirthDate())
                .withPhone(person.getPhone())
                .build();
    }
}
