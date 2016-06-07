package be.jpendel.persistence;

import be.jpendel.domain.event.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

interface EventMongoRepository extends MongoRepository<Event, String> {

}
