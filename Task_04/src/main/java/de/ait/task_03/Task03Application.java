package de.ait.task_03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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