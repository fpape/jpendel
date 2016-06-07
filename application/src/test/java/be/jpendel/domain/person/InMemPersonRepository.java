package be.jpendel.domain.person;

import com.google.common.collect.ImmutableMap;

import java.util.Collection;
import java.util.UUID;

public class InMemPersonRepository implements PersonRepository {

    private ImmutableMap.Builder<UUID, Person> persons = ImmutableMap.builder();


    @Override
    public void add(Person person) {
        persons.put(person.getId(), person);

    }

    @Override
    public Collection<Person> getAll() {
        return this.persons.build().values();
    }
}
