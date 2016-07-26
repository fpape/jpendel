package be.jpendel;

import be.jpendel.application.EventApplicationService;
import be.jpendel.application.EventApplicationServiceImpl;
import be.jpendel.application.PersonApplicationService;
import be.jpendel.domain.event.EventRepository;
import be.jpendel.domain.person.PersonFactory;
import be.jpendel.domain.person.PersonRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public EventApplicationService eventApplicationService(EventRepository eventRepository) {
        return new EventApplicationServiceImpl(eventRepository);
    }

    @Bean
    public PersonApplicationService personApplicationService(PersonRepository personRepository) {
        return new PersonApplicationService(personFactory(), personRepository);
    }

    @Bean
    public PersonFactory personFactory() {
        return new PersonFactory();
    }


}
