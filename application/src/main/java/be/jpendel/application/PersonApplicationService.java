package be.jpendel.application;

import be.jpendel.domain.person.Person;
import be.jpendel.domain.person.PersonFactory;
import be.jpendel.domain.person.PersonRepository;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.UUID;

import static be.jpendel.application.PersonMapper.map;
import static com.google.common.base.Preconditions.checkNotNull;

public class PersonApplicationService {
    private final PersonFactory personFactory;
    private final PersonRepository personRepository;



    public PersonApplicationService(PersonFactory personFactory, PersonRepository personRepository) {
        this.personFactory = personFactory;
        this.personRepository = personRepository;
    }

    public void createPerson(CreatePersonCommand command) {
        checkNotNull(command);
        final Person person = personFactory.create(command);
        personRepository.add(person);
    }

    public List<PersonDTO> getAll() {
        return map(personRepository.getAll());
    }


}
