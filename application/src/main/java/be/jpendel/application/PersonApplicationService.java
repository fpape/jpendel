package be.jpendel.application;

import be.jpendel.domain.person.Person;
import be.jpendel.domain.person.PersonFactory;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.UUID;

import static be.jpendel.application.PersonMapper.map;
import static com.google.common.base.Preconditions.checkNotNull;

public class PersonApplicationService {
    private final PersonFactory personFactory;
    //TODO refactor to repository
    private ImmutableMap.Builder<UUID, Person> persons = ImmutableMap.builder();

    public PersonApplicationService(PersonFactory personFactory) {
        this.personFactory = personFactory;
    }

    public void createPerson(CreatePersonCommand command) {
        checkNotNull(command);
        final Person person = personFactory.create(command);
        persons.put(person.getId(), person);
    }

    public List<PersonDTO> getAll() {
        final ImmutableCollection<Person> persons = this.persons.build().values();
        return map(persons);
    }


}
