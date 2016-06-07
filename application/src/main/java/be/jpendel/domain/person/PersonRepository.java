package be.jpendel.domain.person;

import java.util.Collection;

public interface PersonRepository {
    void add(Person person);

    Collection<Person> getAll();
}
