package be.jpendel;

import be.jpendel.application.EventApplicationService;
import be.jpendel.domain.event.EventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public EventApplicationService eventApplicationService(EventRepository eventRepository){
        return new EventApplicationService(eventRepository);
    }

}
