package be.jpendel.persistence;

import be.jpendel.domain.person.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

interface GenericPersonMongoRepository extends MongoRepository<Person, UUID> {

}
