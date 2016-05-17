package be.jpendel;

import be.jpendel.application.EventApplicationService;
import be.jpendel.application.PersonApplicationService;
import be.jpendel.domain.event.EventFactory;
import be.jpendel.domain.event.EventRepository;
import be.jpendel.domain.person.PersonFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public EventApplicationService eventApplicationService(EventRepository eventRepository) {
        return new EventApplicationService(eventRepository, eventFactory());
    }

    @Bean
    public PersonApplicationService personApplicationService() {
        return new PersonApplicationService(personFactory());
    }

    @Bean
    public EventFactory eventFactory() {
        return new EventFactory();
    }

    @Bean
    public PersonFactory personFactory() {
        return new PersonFactory();
    }


}
