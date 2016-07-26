package be.jpendel.persistence;

import be.jpendel.domain.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
class PersonMongoRepository implements be.jpendel.domain.person.PersonRepository {
    private final GenericPersonMongoRepository genericRepository;

    @Autowired
    PersonMongoRepository(GenericPersonMongoRepository genericRepository) {
        this.genericRepository = genericRepository;
    }

    @Override
    public void add(Person person) {
        genericRepository.insert(person);
    }

    @Override
    public Collection<Person> getAll() {
        return genericRepository.findAll();
    }
}
