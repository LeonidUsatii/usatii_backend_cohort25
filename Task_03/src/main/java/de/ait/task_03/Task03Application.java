package de.ait.task_03;

import de.ait.task_03.dto.EventDto;
import de.ait.task_03.repositories.EventRepository;
import de.ait.task_03.repositories.impl.EventsRepositoryFileImpl;
import de.ait.task_03.services.EventsService;
import de.ait.task_03.services.impl.EventsServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;

@SpringBootApplication
public class Task03Application {
    @Bean
    public WebMvcConfigurer cors() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }


    public static void main(String[] args) {

        SpringApplication.run(Task03Application.class, args);
//        EventRepository eventRepository =
//                new EventsRepositoryFileImpl("events.txt");
//        EventsService eventsService = new EventsServiceImpl(eventRepository);
//
//        eventsService.addEvent(new EventDto("Nord",
//                LocalDate.of(2023, 9, 14),
//                LocalDate.of(2023, 9, 24)));
    }

}
