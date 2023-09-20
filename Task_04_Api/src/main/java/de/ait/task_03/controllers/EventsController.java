package de.ait.task_03.controllers;


import de.ait.task_03.dto.EventDto;
import de.ait.task_03.dto.NewEventDto;
import de.ait.task_03.dto.UpdateEventDto;
import de.ait.task_03.models.Event;
import de.ait.task_03.services.EventsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Tags(value = @Tag(name = "Events"))
@RestController
@RequestMapping("/api/events")
public class EventsController {

    private final EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @Operation(summary = "Добавление нового события", description = "Доступно администратору системы")
    @PostMapping
    public EventDto addEvent(@RequestBody NewEventDto newEvent) {
        LocalDate startDate = getDate(newEvent.getStartDate() + "");
        LocalDate expirationDate = getDate(newEvent.getExpirationDate() + "");
        NewEventDto event = new NewEventDto(newEvent.getTitle(), startDate, expirationDate);
        return eventsService.addEvent(event);
    }
    @Operation(summary = "Получение всех событий", description = "Доступно всем пользователям")
    @GetMapping
    public List<EventDto> getEvents() {
        List<EventDto> events = eventsService.getAllEvents();
        return eventsService.getAllEvents();
    }

    @Operation(summary = "Получение одного события", description = "Доступно всем пользователям")
    @GetMapping("/{event-id}")
    public EventDto getEvent(@Parameter(description = "идентификатор события", example = "4")
                           @PathVariable("event-id") Long id) {
        return eventsService.getEvent(id);
    }

    @Operation(summary = "Обновление события", description = "Доступно только администратору")
    @PutMapping("/{event-id}")
    public EventDto updateEvent(@Parameter(description = "идентификатор события", example = "1")
                              @PathVariable("event-id") Long id,
                               @RequestBody UpdateEventDto updateEvent) {
        return eventsService.updateEvent(id, updateEvent);
    }

    @Operation(summary = "Удаление события", description = "Доступно только администратору")
    @DeleteMapping("/{event-id}")
    public EventDto deleteEvent(@Parameter(description = "идентификатор события", example = "1")
                              @PathVariable("event-id") Long id) {
        return eventsService.deleteEvent(id);
    }


    public static LocalDate getDate(String date) {
        String[] parsed = date.split("-");
        int year = Integer.parseInt(parsed[0]);
        int month = Integer.parseInt(parsed[1]);
        int day = Integer.parseInt(parsed[2]);
        return LocalDate.of(year, month, day);
    }



}
