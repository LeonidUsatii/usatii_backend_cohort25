package de.ait.task_03.controllers;


import de.ait.task_03.dto.EventDto;
import de.ait.task_03.dto.NewEventDto;
import de.ait.task_03.models.Event;
import de.ait.task_03.services.EventsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Tags(value = @Tag(name = "Events"))
@Controller
public class EventsController {

    private final EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @Operation(summary = "Добавление нового события", description = "Доступно администратору системы")
    @PostMapping("/events")
    @ResponseBody
    public EventDto addEvent(@RequestBody NewEventDto newEvent)
                            {
        System.out.println(newEvent);

        LocalDate startDate = getDate(newEvent.getStartDate() + "");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate startDate = LocalDate.parse(newEvent.getStartDate() + "", formatter);
//        LocalDate expirationDate = LocalDate.parse(newEvent.getExpirationDate() + "", formatter);



//        String[] parsed = (newEvent.getStartDate() + "") .split("-");
//        int year = Integer.parseInt(parsed[0]);
//        int month = Integer.parseInt(parsed[1]);
//        int day = Integer.parseInt(parsed[2]);
//        LocalDate startDate = LocalDate.of(year, month, day);
//
//        parsed = (newEvent.getExpirationDate() + "").split("-");
//        year = Integer.parseInt(parsed[0]);
//        month = Integer.parseInt(parsed[1]);
//        day = Integer.parseInt(parsed[2]);
//        LocalDate expirationDate = LocalDate.of(year, month, day);
        LocalDate expirationDate = getDate(newEvent.getExpirationDate() + "");

        NewEventDto event = new NewEventDto(newEvent.getTitle(), startDate, expirationDate);
        return eventsService.addEvent(event);

    }
    @Operation(summary = "Получение всех событий", description = "Доступно администратору системы")
    @GetMapping("/events")
    @ResponseBody
    public List<EventDto> getEvents() {
        List<EventDto> events = eventsService.getAllEvents();

        return eventsService.getAllEvents();
    }

    public static LocalDate getDate(String date) {
        String[] parsed = date.split("-");
        int year = Integer.parseInt(parsed[0]);
        int month = Integer.parseInt(parsed[1]);
        int day = Integer.parseInt(parsed[2]);
        return LocalDate.of(year, month, day);
    }

}
